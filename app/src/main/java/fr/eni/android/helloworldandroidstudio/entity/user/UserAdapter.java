package fr.eni.android.helloworldandroidstudio.entity.user;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import fr.eni.android.helloworldandroidstudio.R;

public class UserAdapter extends ArrayAdapter<BO_User> {

    private Context context;

    public UserAdapter(Context context, List < BO_User > users)
    {
        super(context, 0, users);
        this.context = context;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent){

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_user, parent, false);
        }

        UserViewHolder viewHolder = (UserViewHolder) convertView.getTag();

        if (viewHolder == null)
        {
            viewHolder = new UserViewHolder();
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            viewHolder.lastName = (TextView) convertView.findViewById(R.id.lastName);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
            convertView.setTag(viewHolder);
        }

        BO_User user = getItem(position);
        viewHolder.firstName.setText(user.firstName);
        viewHolder.lastName.setText(user.lastName);

        Resources res = context.getResources();
        Bitmap src = BitmapFactory.decodeResource(res, R.mipmap.ic_user);
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
        viewHolder.photo.setImageDrawable(dr);

        //viewHolder.photo.setImageDrawable(new ColorDrawable(user.color));

        return convertView;
    }

    private class UserViewHolder
    {
        public TextView firstName;
        public TextView lastName;
        public ImageView photo;
    }

}