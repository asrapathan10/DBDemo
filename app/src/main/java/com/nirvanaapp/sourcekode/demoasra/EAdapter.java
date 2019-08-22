package com.nirvanaapp.sourcekode.demoasra;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EAdapter extends RecyclerView.Adapter<EAdapter.ViewHolder> {

    private ArrayList<EmployeeModel> myItems;
    private ItemListener myListener;

    public EAdapter(ArrayList<EmployeeModel> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout_employee, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position));
    }

    public interface ItemListener {
        void onItemClick(EmployeeModel item);
        void onDeleteItem(EmployeeModel item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private EmployeeModel item;
        TextView name;
        TextView salary;
        TextView dept;
        Button delete;


        private ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.textViewName);
            salary = itemView.findViewById(R.id.textViewSalary);
            dept = itemView.findViewById(R.id.textViewDepartment);
            delete = itemView.findViewById(R.id.buttonDeleteEmployee);

        }

        private void setData(final EmployeeModel item) {
            this.item = item;

            name.setText(item.getName());
            salary.setText(item.getSalary());
            dept.setText(item.getDept());

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myListener.onDeleteItem(item);
                }
            });

        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item);
            }
        }
    }


}
