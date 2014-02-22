package com.example.googlemaps;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class CustomLocationManager {

	private static final long UZAKLIK_DEGISIMI = 20;
	private static final long SURE = 1000 * 30;
	private Location mLocation;
	private final LocationManager mLocationManager;
	private final KonumListener mKonumListener;
	private static CustomLocationManager INSTANCE;

	CustomLocationManager(final Context context) {
		// TODO Auto-generated constructor stub
		mLocationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		mKonumListener = new KonumListener();
	}

	public static CustomLocationManager getInstance(final Context context) {
		if (INSTANCE == null) {
			INSTANCE = new CustomLocationManager(context);
		}

		return INSTANCE;
	}

	public void baslaKonumGuncellemesi() {
		final Criteria ktiter = new Criteria();
		ktiter.setAccuracy(Criteria.ACCURACY_COARSE);
		ktiter.setAltitudeRequired(true);
		ktiter.setSpeedRequired(false);
		ktiter.setPowerRequirement(Criteria.POWER_MEDIUM);
		ktiter.setCostAllowed(false);

		String bilgiSaglayici = mLocationManager.getBestProvider(ktiter, true);
		if (bilgiSaglayici == null) {
			final List<String> bilgiSaglayicilar = mLocationManager
					.getAllProviders();
			for (final String tempSaglayici : bilgiSaglayicilar) {
				if (mLocationManager.isProviderEnabled(tempSaglayici)) {
					bilgiSaglayici = tempSaglayici;
				}
			}
		}

		mLocationManager.requestLocationUpdates(bilgiSaglayici, SURE,
				UZAKLIK_DEGISIMI, mKonumListener);

	}

	public void durdurKonumGuncellemesi() {
		if (mLocationManager != null) {
			mLocationManager.removeUpdates(mKonumListener);
		}
	}

	public Location mevcutKonum() {
		return mLocation;
	}

	private class KonumListener implements LocationListener {

		@Override
		public void onLocationChanged(final Location location) {
			// TODO Auto-generated method stub
			mLocation = location;
		}

		@Override
		public void onProviderDisabled(final String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(final String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(final String provider, final int status,
				final Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

}
