package layout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.papcon.papcon.MainActivity;
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

import butterknife.ButterKnife;

public class RecommendFragment extends Fragment {

    private static final String ARG_NAME = "recommend";

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

        String[] menuItems = {"안녕", "하이", "오하이오", "할라",
                "안녕", "하이", "오하이오", "할라",
                "안녕", "하이", "오하이오", "할라",
                "안녕", "하이", "오하이오", "할라"};
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        listView.setAdapter(listViewAdapter);
        return rootView;
    }
}
