package com.mirea.sumachev.mireaproject;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PoemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PoemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView titleView;
    TextView bodyView;
    Document doc;
    Elements content;
    Elements titleElement;
    String poem = "";
    String poemTitle = "";

    public PoemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PoemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PoemFragment newInstance(String param1, String param2) {
        PoemFragment fragment = new PoemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poem, container, false);

        titleView = view.findViewWithTag(R.id.textViewTitle);
        bodyView = view.findViewById(R.id.textViewBody);

        DownloadPoem downloadPoem = new DownloadPoem();
        downloadPoem.execute();

        return view;
    }


    private class DownloadPoem extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                doc = Jsoup.connect("https://www.culture.ru/poems/14037/nikogo-ne-budet-v-dome")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36")
                        .referrer("https://yandex.ru/").get();
                content = doc.select(".xZmPc");
                titleElement = doc.select(".xtEsw");
                for (Element elements : content) {
                    poem += elements.text();
                }
                for (Element elements : titleElement) {
                    poemTitle += elements.text();
                }

            } catch (IOException e) {
                Log.e("Error", "Can't get data");
            }
            Log.d("OutputTitle", poemTitle);
            Log.d("OutputBody", poem);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(), "Загрузка стихотворения...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            bodyView.setText(poem);

            Toast.makeText(getActivity(), "Загрузка стихотворения завершена", Toast.LENGTH_SHORT).show();
        }

    }

}