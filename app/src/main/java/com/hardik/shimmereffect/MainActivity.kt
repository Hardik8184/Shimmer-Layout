package com.hardik.shimmereffect

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.hardik.shimmereffect.ApiClient.client
import kotlinx.android.synthetic.main.activity_main.mShimmerViewContainer
import kotlinx.android.synthetic.main.activity_main.recyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
  private var cartList: MutableList<RecipeResponse>? = null
  private var mAdapter: RecipeListAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    cartList = ArrayList()
    mAdapter = RecipeListAdapter(this, cartList!!)

    val mLayoutManager: LayoutManager = LinearLayoutManager(applicationContext)
    recyclerView.layoutManager = mLayoutManager
    recyclerView.itemAnimator = DefaultItemAnimator()
    recyclerView.addItemDecoration(
        MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16)
    )
    recyclerView.adapter = mAdapter
    // making http call and fetching menu json
    fetchRecipes()
  }

  /**
   * method make retrofit network call and parses json
   */
  private fun fetchRecipes() {

    val apiService = client!!.create(ApiInterface::class.java)
    val call: Call<ArrayList<RecipeResponse>> = apiService.fetchRecipes()

    call.enqueue(object : Callback<ArrayList<RecipeResponse>> {
      override fun onResponse(
        call: Call<ArrayList<RecipeResponse>>,
        response: Response<ArrayList<RecipeResponse>>
      ) {

        val recipesList = response.body()
        cartList!!.clear()
        cartList!!.addAll(recipesList!!)
        // refreshing recycler view
        mAdapter!!.notifyDataSetChanged()
        // stop animating Shimmer and hide the layout
        mShimmerViewContainer!!.stopShimmerAnimation()
        mShimmerViewContainer!!.visibility = View.GONE
      }

      override fun onFailure(
        call: Call<ArrayList<RecipeResponse>>,
        t: Throwable
      ) { // Log error here since request failed
        Log.e(TAG, t.toString())
        Toast.makeText(this@MainActivity, "Error : " + t.localizedMessage, Toast.LENGTH_SHORT)
            .show()
      }
    })
  }

  public override fun onResume() {
    super.onResume()
    mShimmerViewContainer!!.startShimmerAnimation()
  }

  public override fun onPause() {
    mShimmerViewContainer!!.stopShimmerAnimation()
    super.onPause()
  }

  companion object {
    private val TAG = MainActivity::class.java.simpleName
  }
}