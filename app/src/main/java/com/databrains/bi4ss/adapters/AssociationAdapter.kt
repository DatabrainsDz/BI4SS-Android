package com.databrains.bi4ss.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.databrains.bi4ss.R
import com.databrains.bi4ss.models.Response

class AssociationAdapter(private val data: List<Response>) : RecyclerView.Adapter<AssociationAdapter.AssociationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, poisition: Int): AssociationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.association_item, parent, false)
        return AssociationViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AssociationViewHolder, position: Int) {

    }


    inner class AssociationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val courseTextView = view.findViewById<TextView>(R.id.subjectTextView)
        val relatedCourseTextView = view.findViewById<TextView>(R.id.relatedSubjectTextView)
    }
}