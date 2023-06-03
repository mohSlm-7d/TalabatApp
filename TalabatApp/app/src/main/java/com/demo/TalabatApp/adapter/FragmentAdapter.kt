package com.demo.foodorderanddeliveryappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.foodorderanddeliveryappkotlin.R

class FragmentAdapter(manager: FragmentManager) : RecyclerView.Adapter<FragmentAdapter.FragmentViewHolder>() {
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val fragmentManager: FragmentManager = manager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment, parent, false)
        return FragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        val fragment = fragmentList[position]
        fragmentManager.beginTransaction()
            .replace(holder.container.id, fragment)
            .commit()
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
    fun replaceFragment(fragment: Fragment) {
        if (fragmentList.isNotEmpty()) {
            fragmentList[fragmentList.size - 1] = fragment
        } else {
            fragmentList.add(fragment)
        }
    }

    inner class FragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: FrameLayout = itemView.findViewById(R.id.fragmentContainer)
    }
}
