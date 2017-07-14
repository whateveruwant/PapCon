package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.papcon.papcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestFragment extends Fragment {
    private static final String ARG_NAME = "request";

    @BindView(R.id.textView)
    TextView textView;

    public static RequestFragment newInstance() {
        RequestFragment fragment = new RequestFragment();
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
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this, rootview);
        ViewCompat.setElevation(rootview, 50);
        textView.setText("요청하기");
        return rootview;
    }
}
