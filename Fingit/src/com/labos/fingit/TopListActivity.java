package com.labos.fingit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TopListActivity extends Activity {

	private ListView listView;
//	private String[] topn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toplist);

		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.listTop);
		Bundle args = getIntent().getExtras();
		// Defined Array values to show in ListView
		String[] values = args.getStringArray("topn");
//		for (int i = 0; i < topn.size(); i++) {
//			values[i] = (i + 1) + ". " + topn.get(i).getHash() + " "
//					+ topn.get(i).getPuntos();
//		}

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition = position;

				// ListView Clicked item value
				String itemValue = (String) listView
						.getItemAtPosition(position);

				// Show Alert
				Toast.makeText(
						getApplicationContext(),
						"Position :" + (itemPosition+1) + "  ListItem : "
								+ itemValue, Toast.LENGTH_LONG).show();
			}

		});
	}

}
