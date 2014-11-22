package br.gov.serpro.cloudmessagingtest.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static br.gov.serpro.cloudmessagingtest.app.R.layout.bookmark_list_item;

/**
 * Created by DaniloCosta on 16/04/2014.
 */
public class BookmarkListAdapter extends ArrayAdapter<Bookmark> {

    private LayoutInflater inflater;

    public BookmarkListAdapter(Context context) {
        super(context, R.id.labelDescricaoLink);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null){
            inflater = LayoutInflater.from(getContext());
        }

        View itemView;
        if (convertView!=null && RelativeLayout.class.isInstance(convertView)){
            itemView = convertView;
        }
        else{
            itemView = inflater.inflate(R.layout.bookmark_list_item,parent,false);
        }

        Bookmark b = getItem(position);
        if (b != null && itemView != null){
            TextView campoTexto = (TextView) itemView.findViewById(R.id.labelDescricaoLink);
            campoTexto.setText(b.getDescription());

            campoTexto = (TextView) itemView.findViewById(R.id.labelLink);
            campoTexto.setText(b.getLink());
        }

        return itemView;
    }


}
