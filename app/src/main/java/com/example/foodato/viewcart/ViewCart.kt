@file:Suppress("DEPRECATION")

package com.example.foodato.viewcart

import android.app.AlertDialog
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodato.NotificationJobScheduler
import com.example.foodato.R
import com.example.foodato.databinding.FragmentViewCartBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Suppress("DEPRECATION")
class ViewCart : Fragment() {
    private var JobId = 0
    private lateinit var binding: FragmentViewCartBinding
    private lateinit var adapter: ViewCartAdapter
    private lateinit var data: ArrayList<CartData>
    private lateinit var viewModel: ViewCartViewModel
    private lateinit var scheduler: JobScheduler

    var orderDetails = "Order Details\n"
    private lateinit var finalDishName: String
    private lateinit var dishPrice: String
    private var basic: String = ""
    var subTotal = "Subtotal"
    var taxValue = "Total Tax"
    var totalValue = "Total"

    //var count=0
    //    lateinit var name:String
//     var mPrice:Int=0
    var prevPrice = 0
    var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = prefData()
        Log.e(this.toString(), "33lllllllllll33333${data.forEach { it.getDishName() }}")

        val viewCartModelFactory = ViewCartModelFactory(data)
        viewModel = ViewModelProvider(this, viewCartModelFactory).get(ViewCartViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_cart, container, false)

        data = prefData()

        totalPriceSimpleAdd(data)

        adapter =
            ViewCartAdapter(ViewCartAdapter.Clicklisteners { price, dishName ->

                Log.e(this.toString(), "33333333333333$dishName")
                clearValue(price, dishName)

            })
        binding.recycler.adapter = adapter
        viewModel.arrayData.value = data


        viewModel._arrayData.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)

                Log.e(this.toString(), "pppppppppp33333333$it")

                //                adapter.data=it
            }
        })


        binding.lifecycleOwner = this
        return binding.root
    }


    private fun clearValue(price: Int, dishName: String) {
        viewModel._arrayData.observe(this, Observer {
            basic = ""
            if (it.count() > 0) {

                for (i in it) {
                    if (i.getDishName() == dishName) {
                        Log.e(this.toString(), "JayEEHHHHHHHHHH${i}")

                        it.remove(i)
                        checkoutPricePressCross(price)
                        break

                    } else {
                        finalDishName = i.getDishName()
                        dishPrice = i.getPrice()
                        basic += finalDishName + "\t" + dishPrice + "\n"


                    }


                    var list = it

                    Log.e(this.toString(), "MEEEjjjjjjjjHH${list.count()}")

                }
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                basic += subTotal + " ₹$prevPrice\n" + taxValue + " ₹$tax\n" + totalValue + " ₹${prevPrice+tax}"
                Log.e(this.toString(), "Kakkar${basic}")
                var orderDetails = "Order Details\n"

                orderDetails += basic
                var intent = Intent(Intent.ACTION_SENDTO);
                intent = ShareCompat.IntentBuilder.from(requireActivity()).setText(orderDetails)
                    .setType("text/plain").intent
                binding.checkout.setOnClickListener {

                    var alertDialog = AlertDialog.Builder(context)
                    alertDialog.setMessage("Do you want to checkout")
                    alertDialog.setNegativeButton(
                        "yes",
                        DialogInterface.OnClickListener { dialog, which ->
                            val appSharedPrefs = PreferenceManager
                                .getDefaultSharedPreferences(context)
                            val prefsEditor = appSharedPrefs.edit()

//                            NotificationJobScheduler().scheduleJob()
//scheduleJob()

                            prefsEditor.putString ("Details", "")
                            prefsEditor.apply()
                            startActivity(intent)

                            var data = prefData()
                            totalPriceSimpleAdd(data)
                            adapter.submitList(data)
                            adapter.notifyDataSetChanged()


                        })
                    alertDialog.setPositiveButton(
                        "no",
                        DialogInterface.OnClickListener { dialog, which ->
                        })
                    alertDialog.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
                val appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(context)
                val prefsEditor = appSharedPrefs.edit()

                val gson = Gson()
                val json: String = gson.toJson(it)
                Log.e(this.toString(), "JayEEHHHHHHHHHH${json}")

                prefsEditor.putString("Details", json)
                prefsEditor.apply()
            } else {

            }


        })


    }


    fun checkoutPricePressCross(price: Int) {


        if (prevPrice > 0) {
            prevPrice -= price
            binding.emptyCart.visibility = View.INVISIBLE
            tax = 0.05 * prevPrice
            var finalPrice = prevPrice + tax

            binding.taxprice.text = "₹${tax}"
            binding.subtotalPrice.text = "₹${prevPrice}"
            binding.subtotalPrice.visibility = View.VISIBLE
            binding.subtotal.visibility = View.VISIBLE
            binding.tax.visibility = View.VISIBLE
            binding.taxprice.visibility = View.VISIBLE
            binding.view.visibility = View.VISIBLE
            binding.total.text = finalPrice.toString()
            binding.total.visibility = View.VISIBLE
            binding.symbol.visibility = View.VISIBLE
        }
        if (prevPrice == 0) {
            binding.total.visibility = View.INVISIBLE
            binding.symbol.visibility = View.INVISIBLE
            binding.emptyCart.visibility = View.VISIBLE

            binding.subtotalPrice.visibility = View.GONE
            binding.subtotal.visibility = View.GONE
            binding.tax.visibility = View.GONE
            binding.taxprice.visibility = View.GONE
            binding.view.visibility = View.GONE
        }

    }

    fun totalPriceSimpleAdd(details: ArrayList<CartData>) {
        var intent = Intent(Intent.ACTION_SENDTO);

        if (details.isNotEmpty()) {
            details.forEach {
                prevPrice += it.getPriceInt()
                finalDishName = it.getDishName()
                dishPrice = it.getPrice()
                basic += finalDishName + "\t" + dishPrice + "\n"
            }

            tax = 0.05 * prevPrice
            var finalPrice = prevPrice + tax
            basic += subTotal + " ₹$prevPrice\n" + taxValue + " ₹$tax\n" + totalValue + " ₹${finalPrice}"
            orderDetails += basic
            intent = ShareCompat.IntentBuilder.from(requireActivity()).setText(orderDetails)
                .setType("text/plain").intent

            binding.taxprice.text = "₹${tax}"
            binding.subtotalPrice.text = "₹${prevPrice}"
            binding.subtotalPrice.visibility = View.VISIBLE
            binding.subtotal.visibility = View.VISIBLE
            binding.tax.visibility = View.VISIBLE
            binding.taxprice.visibility = View.VISIBLE
            binding.view.visibility = View.VISIBLE
            binding.emptyCart.visibility = View.INVISIBLE

            binding.total.text = finalPrice.toString()
            binding.total.visibility = View.VISIBLE
            binding.symbol.visibility = View.VISIBLE


        } else {
            binding.total.visibility = View.INVISIBLE
            binding.symbol.visibility = View.INVISIBLE
            binding.emptyCart.visibility = View.VISIBLE

            binding.subtotalPrice.visibility = View.GONE
            binding.subtotal.visibility = View.GONE
            binding.tax.visibility = View.GONE
            binding.taxprice.visibility = View.GONE
            binding.view.visibility = View.GONE

        }
        binding.checkout.setOnClickListener {
            if (details.isEmpty()) {
                Toast.makeText(requireContext(), "Cart is empty", Toast.LENGTH_SHORT).show()
            } else {


                var alertDialog = AlertDialog.Builder(context)
                alertDialog.setMessage("Do you want to checkout")
                alertDialog.setNegativeButton(
                    "yes",
                    DialogInterface.OnClickListener { dialog, which ->
                        val appSharedPrefs = PreferenceManager
                            .getDefaultSharedPreferences(context)
                        val prefsEditor = appSharedPrefs.edit()


                        prefsEditor.putString("Details", "")
                        prefsEditor.apply()

                        scheduleJob()
//                        NotificationJobScheduler().scheduleJob()
//NotificationJobScheduler()

                        startActivity(intent)
                        var data = prefData()
                        totalPriceSimpleAdd(data)
                        adapter.submitList(data)
                        adapter.notifyDataSetChanged()


                    })
                alertDialog.setPositiveButton(
                    "no",
                    DialogInterface.OnClickListener { dialog, which ->
                    })
                val alertDialogBuilder: AlertDialog = alertDialog.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }

    private fun prefData(): ArrayList<CartData> {

        val appSharedPrefsget = PreferenceManager
            .getDefaultSharedPreferences(context)
        val detail: ArrayList<CartData>
        val gsonG = Gson()
        val jsonG = appSharedPrefsget.getString("Details", "")

        detail = if (jsonG?.isEmpty() == true) {

            ArrayList()
        } else {
            val type = object : TypeToken<ArrayList<CartData>>() {}.type
            gsonG.fromJson(jsonG, type)
        }
        return detail

    }

    fun scheduleJob() {

        val netWorkGroup = JobInfo.NETWORK_TYPE_UNMETERED
        scheduler = activity?.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
//component name is used to associate Job service with job info
        var componentName = ComponentName(requireActivity().packageName, NotificationJobScheduler::class.java.name)
//        Log.e(this.toString(),"333333333333333333${componentName}")

        val builder=JobInfo.Builder(JobId, componentName)
            .setRequiredNetworkType(netWorkGroup)
            .setRequiresDeviceIdle(true)
        builder.setOverrideDeadline(( 10000).toLong());//1000.toLong=1second

        Log.e(this.toString(),"22222222222222222222$builder")

        val myJobInfo=builder.build()
        scheduler.schedule(myJobInfo)
        Toast.makeText(
            activity, "Job Scheduled, job will run when " +
                    "the constraints are met.", Toast.LENGTH_SHORT
        ).show()
    }




}