package com.pinetree.mobile.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.pinetree.mobile.R;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.util.Log;

public class AssetHelper {
	private static final String TAG = "AssetHelper";

	static public void CopyAsset(Context ctx, File path, String filename) throws IOException {
		AssetManager assetManager = ctx.getAssets();
		InputStream in = null;
		OutputStream out = null;

		// Copy files from asset folder to application folder
		try {
			in = assetManager.open(filename);
			out = new FileOutputStream(path.toString() + "/" + filename);
			copyFile(in, out);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		} finally {
			// Reclaim resources
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	static private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;

		// Copy from input stream to output stream
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	static public File getPath(Context ctx, boolean externalStorage) {
		if (externalStorage) {
			return ctx.getExternalFilesDir(null);
		} else {
			return ctx.getFilesDir();
		}
	}

	static public void showAlert(Context ctx, final String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
		alertDialog.setTitle("Application Error");
		alertDialog.setMessage(message);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Dismiss", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing
			}
		});
		alertDialog.show();
	}

	static public String getDataBase(Context ctx, String filename) throws FileNotFoundException {
		File db = null;

		// Check application storage first
		db = new File(getPath(ctx, false), filename);
		Log.d(TAG, "Checking: " + db.toString());
		if (db.exists()) {
			return db.toString();
		}

		// Check external storage second
		db = new File(getPath(ctx, true), filename);
		Log.d(TAG, "Checking: " + db.toString());
		if (db.exists()) {
			return db.toString();
		}

		// Database not found
		throw new FileNotFoundException(ctx.getString(R.string.db_error));
	}
}
