package fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.papcon.papcon.ManagementActivity;
import com.papcon.papcon.R;
import com.papcon.papcon.User;
import com.papcon.papcon.UserListAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;

public class RecommendFragment extends Fragment {

    private static final String ARG_NAME = "recommend";

    private ListView listView;
    private ArrayList<User> userList;
    private String dataStr;
    String target;

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, ARG_NAME);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, rootView);
        ViewCompat.setElevation(rootView, 50);

        target = "http://dbals1630.cafe24.com/List.php";

        listView = (ListView) rootView.findViewById(R.id.listView);
        userList = new ArrayList<User>();

        dataStr = "";
        BackgroundTaskforTable making = new BackgroundTaskforTable();
        making.execute();
        try {
            dataStr = making.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count =0;
            String userID, userPassword, userName, userAge;
            while(count<jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                userID =object.getString("userID");
                userPassword =object.getString("userPassword");
                userName =object.getString("userName");
                userAge =object.getString("userAge");
                userList.add(new User(userID, userPassword, userName, userAge));
                count++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        UserListAdapter userListAdapter = new UserListAdapter(rootView.getContext(), userList);
        listView.setAdapter(userListAdapter);

        //String[] menuItems = {"안녕", "하이", "오하이오", "할라",
        //        "안녕", "하이", "오하이오", "할라",
        //        "안녕", "하이", "오하이오", "할라",
        //        "안녕", "하이", "오하이오", "할라"};
        //ListView listView = (ListView) rootView.findViewById(R.id.listView);
        //ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
        //        getActivity(),
        //        android.R.layout.simple_expandable_list_item_1,
        //        menuItems
        //);
        //listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new BackgroundTask().execute();
            }
        });
        return rootView;
    }

    class BackgroundTaskforTable extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
        }
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            Intent intent = new Intent(getActivity(), ManagementActivity.class);
            intent.putExtra("userList", result);
            getActivity().startActivity(intent);
        }
    }
}
