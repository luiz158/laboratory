package br.gov.serpro.cloudmessagingtest.app;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private BookmarkListAdapter listaBookmarksAdapter;
		private ListView listaBookmarksView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			listaBookmarksView = (ListView) rootView.findViewById(R.id.listaBookmarks);

			loadBookmarkList();

			return rootView;
		}
		
		protected void loadBookmarkList(){
			if (listaBookmarksAdapter==null){
				listaBookmarksAdapter = new BookmarkListAdapter(getActivity());
			}
			else{
				listaBookmarksAdapter.clear();
			}
			
			listaBookmarksView.setAdapter(listaBookmarksAdapter);
			for (Bookmark b : getBookmarks()){
				listaBookmarksAdapter.add(b);
			}
		}
		
		 /**
	     * Faz uma consulta ao servidor para obter a lista de bookmarks cadastrados
	     *
	     * @return A lista (talvez vazia) de bookmarks cadastrados
	     */
	    protected Collection<Bookmark> getBookmarks(){
	        final ArrayList<Bookmark> listaBookmark = new ArrayList<Bookmark>();

	        AsyncTask<Object,Void,Bookmark[]> restRequestTask = new AsyncTask<Object,Void,Bookmark[]>() {

	            Gson jsonReader = new Gson();

	            @Override
	            protected Bookmark[] doInBackground(Object... params) {
	                URL bookmarkServer = null;
	                try {
	                    bookmarkServer = new URL(getResources().getString(R.string.server_url));
	                } catch (MalformedURLException e) {
	                    //TODO Implementar tratamento de erro
	                    e.printStackTrace();
	                    bookmarkServer = null;
	                }

	                if (bookmarkServer != null){
	                    HttpURLConnection serverConnection = null;
	                    try {
	                        serverConnection = (HttpURLConnection) bookmarkServer.openConnection();

	                        serverConnection.setRequestMethod("GET");
	                        serverConnection.addRequestProperty("Content-Type","application/json");
	                        serverConnection.setChunkedStreamingMode(0);

	                        InputStream is = serverConnection.getInputStream();
	                        if (is != null){
	                            is = new BufferedInputStream(is);

	                            StringBuffer jsonReturn = new StringBuffer();
	                            byte[] buffer = new byte[1024];

	                            int size = is.read(buffer);
	                            while (size>0){
	                                jsonReturn.append(new String(buffer,0,size));
	                                size = is.read(buffer);
	                            }

	                            is.close();

	                            Bookmark[] bookmarks = jsonReader.fromJson(jsonReturn.toString(),Bookmark[].class);
	                            return bookmarks;
	                        }

	                    } catch (Exception e) {
	                        //TODO Implementar tratamento de erro
	                        e.printStackTrace();
	                    }
	                    finally {
	                        if (serverConnection!=null){
	                            serverConnection.disconnect();
	                        }
	                    }
	                }

	                return null;
	            }
	        };

	        try {
	            Bookmark[] bookmarks = restRequestTask.execute().get();
	            if (bookmarks != null) {
	                for (Bookmark b : bookmarks) {
	                    listaBookmark.add(b);
	                }
	            }
	        } catch (Exception e) {
	            //TODO Implementar tratamento de erro
	            e.printStackTrace();
	        }

	        return listaBookmark;
	    }
	}

}
