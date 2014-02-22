package com.example.googlemaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity {
	GoogleMap harita;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (harita == null) {
			harita = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.haritaFragment)).getMap();
			harita.setMyLocationEnabled(true);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.normal_gorunum:
			harita.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.hibrid_gorunum:
			harita.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.uydu_gorunum:
			harita.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.terrain_gorunum:
			harita.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		}
		return true;
	}
}
