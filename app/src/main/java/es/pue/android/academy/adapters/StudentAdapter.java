package es.pue.android.academy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import es.pue.android.academy.R;
import es.pue.android.academy.model.Student;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Student> students;
    private int layout;

    public StudentAdapter(Context context, int layout, List<Student> students) {
        this.context = context;
        this.layout = layout;
        this.students = students;
    }

    @Override
    public int getCount() {
        return this.students.size();
    }

    @Override
    public Object getItem(int position) {
        return this.students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * We use the ViewHolder pattern here to save memory and reuse the shown items on scroll.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(this.layout, null);

            vh = new ViewHolder();
            vh.tvName = convertView.findViewById(R.id.tvName);
            vh.tvAge = convertView.findViewById(R.id.tvAge);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Student student = (Student) this.getItem(position);
        vh.tvName.setText(student.getName());
        vh.tvAge.setText(String.valueOf(student.getAge()));

        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
        TextView tvAge;


    }
}
