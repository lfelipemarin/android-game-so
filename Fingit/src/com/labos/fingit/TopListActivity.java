package com.labos.fingit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TopListActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toplist);

		listView = (ListView) findViewById(R.id.listTop);
		Bundle args = getIntent().getExtras();

		String[] values = args.getStringArray("topn");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		listView.setAdapter(adapter);

		// listView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		//
		// // ListView Clicked item index
		// int itemPosition = position;
		//
		// // ListView Clicked item value
		// String itemValue = (String) listView
		// .getItemAtPosition(position);
		//
		// // Show Alert
		// Toast.makeText(
		// getApplicationContext(),
		// "Position :" + (itemPosition+1) + "  ListItem : "
		// + itemValue, Toast.LENGTH_LONG).show();
		// }
		//
		// });
	}

}
