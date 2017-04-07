package ke.co.appslab.manuel.geeksblog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ManuEl on 3/26/2017.
 */

public class CustonAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;
    CustonAdapter(Context context, List<RowItem> rowItems){
        this.context = context;
        this.rowItems = rowItems;
    }
    @Override
    public int getCount(){ return rowItems.size();}

    @Override
    public Object getItem(int position){ return rowItems.get(position);}

    @Override
    public  long getItemId(int position){ return rowItems.indexOf(getItem(position));}

    private class ViewHolder{
        ImageView profile_pic;
        TextView member_name;
        TextView status;
        TextView contactType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        LayoutInflater mInflator = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflator.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.member_name = (TextView) convertView
                    .findViewById(R.id.member_name);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_pic);
            holder.status = (TextView) convertView
                    .findViewById(R.id.status);
            holder.contactType = (TextView) convertView
                    .findViewById(R.id.contact_type);

            RowItem row_pos = rowItems.get(position);

            holder.profile_pic.setImageResource(row_pos.getProfile_pic_id());
            holder.member_name.setText(row_pos.getMember_name());
            holder.status.setText(row_pos.getStatus());
            holder.contactType.setText(row_pos.getContactType());

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

}
