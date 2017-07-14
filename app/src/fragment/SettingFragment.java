package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.papcon.papcon.GuideActivity;
import com.papcon.papcon.InformActivity;
import com.papcon.papcon.InquireActivity;
import com.papcon.papcon.InventoryActivity;
import com.papcon.papcon.LoginActivity;
import com.papcon.papcon.MessageActivity;
import com.papcon.papcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment {
    private static final String ARG_NAME = "setting";

    @BindView(R.id.listView)
    ListView listView;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, rootView);
        ViewCompat.setElevation(rootView, 50);

        String[] menuItems = {"내 정보", "신청목록", "이용방법", "메세지함",
                "문의하기", "로그아웃"};
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 내정보
                        Intent informIntent = new Intent(getActivity(), InformActivity.class);
                        getActivity().startActivity(informIntent);
                        break;
                    case 1: // 신청목록
                        Intent inventoryIntent = new Intent(getActivity(), InventoryActivity.class);
                        getActivity().startActivity(inventoryIntent);
                        break;
                    case 2: // 이용방법
                        Intent guideIntent = new Intent(getActivity(), GuideActivity.class);
                        getActivity().startActivity(guideIntent);
                        break;
                    case 3: // 메세지함
                        Intent messageIntent = new Intent(getActivity(), MessageActivity.class);
                        getActivity().startActivity(messageIntent);
                        break;
                    case 4: // 문의하기
                        Intent inquireIntent = new Intent(getActivity(), InquireActivity.class);
                        getActivity().startActivity(inquireIntent);
                        break;
                    case 5: // 로그아웃
                        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(loginIntent);
                        break;
                }
        }
        });
        return rootView;
    }
}
