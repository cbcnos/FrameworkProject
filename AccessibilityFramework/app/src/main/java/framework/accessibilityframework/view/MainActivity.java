package framework.accessibilityframework.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import framework.accessibilityframework.R;
import framework.accessibilityframework.view.touch.FeedActivity;
import framework.accessibilityframework.view.touch.SquareActivity;
import framework.accessibilityframework.view.touch.TomatoActivity;
import framework.accessibilityframework.view.touch.TouchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.main_list);
        list.setAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            switch (position) {
                case 0:
                    ((ImageView)convertView.findViewById(R.id.list_image)).setImageResource(R.drawable.tomato);
                    ((TextView)convertView.findViewById(R.id.list_title)).setText(R.string.title1);
                    ((TextView)convertView.findViewById(R.id.list_desc)).setText(R.string.desc1);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(MainActivity.this, TomatoActivity.class));
                        }
                    });
                    break;
                 case 1:
                    ((ImageView)convertView.findViewById(R.id.list_image)).setImageResource(R.drawable.monster_left);
                    ((TextView)convertView.findViewById(R.id.list_title)).setText(R.string.title2);
                    ((TextView)convertView.findViewById(R.id.list_desc)).setText(R.string.desc2);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(MainActivity.this, FeedActivity.class));
                        }
                    });
                    break;
                 case 2:
                    ((ImageView)convertView.findViewById(R.id.list_image)).setImageResource(R.drawable.square_tumb);
                    ((TextView)convertView.findViewById(R.id.list_title)).setText(R.string.title3);
                    ((TextView)convertView.findViewById(R.id.list_desc)).setText(R.string.desc3);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(MainActivity.this, SquareActivity.class));
                        }
                    });
                    break;
                 case 3:
                    ((ImageView)convertView.findViewById(R.id.list_image)).setImageResource(R.drawable.touch_tumb);
                    ((TextView)convertView.findViewById(R.id.list_title)).setText(R.string.title4);
                    ((TextView)convertView.findViewById(R.id.list_desc)).setText(R.string.desc4);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(MainActivity.this, TouchActivity.class));
                        }
                    });
                    break;
            }
            return convertView;
        }
    }
}
