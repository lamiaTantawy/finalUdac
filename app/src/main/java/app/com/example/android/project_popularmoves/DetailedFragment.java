package app.com.example.android.project_popularmoves;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class DetailedFragment extends Fragment  {
    ImageView imageView;
    TextView textView;
    TextView overview;
    TextView release;
    Button favourite_bt;
    Button RemoveFavourite_bt;
    Movie listOfMovie;
    ExpandableListView lv;
    ExpandableListView TrailarExtandable;
    private String[] groups;
    private String[][] children;
    private String[] groupTrailar;
    private String[][] childrenTrailar;
    private List<Trailer> Trailars;
    private List<String> GroupReviews;
    private List<String> contentReviews;
    private Realm mRealm;
    public DetailedFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            Bundle bundle = getArguments();
            listOfMovie = (Movie) bundle.get("Movies");
        }
        else
        listOfMovie = (Movie) getActivity().getIntent().getParcelableExtra("Movies");
        ////////////////////////////////////////
        AsyncTrailersBackground async = new AsyncTrailersBackground();
        async.execute(listOfMovie.getID());
        try {
            Trailars = async.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////
        AsyncReviewsBackground asyncReview = new AsyncReviewsBackground();
        asyncReview.execute(listOfMovie.getID());
        try {
            contentReviews = asyncReview.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////
        groupTrailar = new String[] {"Trailars"};
        childrenTrailar = new String[1][Trailars.size()];
        for(int i=0;i<Trailars.size();i++)
        {
            childrenTrailar[0][i] = Trailars.get(i).getName();
        }

        GroupReviews = new ArrayList<String>();
        for(int i=0;i<contentReviews.size();i++) {
            GroupReviews.add("Review"+(i+1));
        }
        groups = GroupReviews.toArray(new String[GroupReviews.size()]);
        children = new String[GroupReviews.size()][1];
        for(int i=0;i<GroupReviews.size();i++)
        {
            children[i][0] = contentReviews.get(i);
        }
        ////////////////
        mRealm = Realm.getInstance(getContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getContext())
                .deleteRealmIfMigrationNeeded()
                .build();
//        try {
//            mRealm = Realm.getInstance(getContext());
//        }catch (RealmMigrationNeededException e){
//            try {
//                Realm.deleteRealm(realmConfiguration);
//                //Realm file has been deleted.
//                Realm.getInstance(realmConfiguration);
//            } catch (Exception ex){
//                throw ex;
//                //No Realm file to remove.
//            }
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailed, container, false);

     //   TrailarExtandable = (ExpandableListView) rootView.findViewById(R.id.expListView1);
     //   lv = (ExpandableListView) rootView.findViewById(R.id.expListView);

        imageView = (ImageView)rootView.findViewById(R.id.detailedImg);
        textView = (TextView)rootView.findViewById(R.id.detailTitle);
        overview = (TextView)rootView.findViewById(R.id.detailedOverview);
        release = (TextView)rootView.findViewById(R.id.release);
        favourite_bt = (Button)rootView.findViewById(R.id.fav_bt);
        favourite_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealm.beginTransaction();
                FavoritMovie favMovie = mRealm.createObject(FavoritMovie.class);
                favMovie.setId(listOfMovie.getID());
                favMovie.setOverview(listOfMovie.getOverview());
                favMovie.setReleaseData(listOfMovie.getReleaseData());
                favMovie.setTitle(listOfMovie.getTitle());
                favMovie.setUserRating(listOfMovie.getUserRating());
                favMovie.setImageURL(listOfMovie.getImageURL());
                mRealm.commitTransaction();
            }
        });
        RemoveFavourite_bt = (Button)rootView.findViewById(R.id.Remfav_bt);
        RemoveFavourite_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealm.beginTransaction();
                RealmResults<FavoritMovie> fav = mRealm.where(FavoritMovie.class).equalTo("Id", listOfMovie.getID()).findAll();
                if(!fav.isEmpty()) {
                    for(int i = fav.size() - 1; i >= 0; i--) {
                        fav.get(i).removeFromRealm();
                    }
                }
                mRealm.commitTransaction();
            }
        });
        Picasso.with(getActivity()).load(listOfMovie.getImageURL()).into(imageView);
        textView.setText(listOfMovie.getTitle());
        overview.setText(listOfMovie.getOverview());
        release.setText("Release date : "+listOfMovie.getReleaseData()+"\nRating :"+listOfMovie.getUserRating());




        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TrailarExtandable = (ExpandableListView) view.findViewById(R.id.expListView1);
        TrailarExtandable.setAdapter(new ExpandableListAdapter(groupTrailar, childrenTrailar,getActivity()));
        TrailarExtandable.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(), " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TrailarExtandable.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(), " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        TrailarExtandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+Trailars.get(childPosition).getKey()));

                startActivity(intent);
                return false;
            }
        });
        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(groups, children,getActivity()));
        lv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(), " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(), " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mRealm.close();
//    }
}
