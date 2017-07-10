package ahmedpro.com.asbabelnzolv1.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.lang.reflect.Method;

import ahmedpro.com.asbabelnzolv1.R;

/**
 * Created by hp on 14/06/2017.
 */

public class ArrayListFragment extends Fragment implements View.OnClickListener {

    String mMessage;
    WebView webView;
    private LinearLayout container;
    private Button nextButton, closeButton;
    private EditText findBox;
    ScrollView scrollView;
    boolean flag = true;
    ViewGroup linearLayout;


    public static ArrayListFragment newInstance(String message) {
        ArrayListFragment f = new ArrayListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("message", message);
        f.setArguments(args);

        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DownloadData().execute();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_pager, container, false);
        // View tv = v.findViewById(R.id.webView);
        webView = (WebView) v.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        container = (LinearLayout) v.findViewById(R.id.layoutId);
       // container.setVisibility(LinearLayout.GONE);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView2);
        webView.loadDataWithBaseURL(null, mMessage, "text/html", "utf-8", null);

        nextButton = (Button) v.findViewById(R.id.nextButton);
        closeButton = (Button) v.findViewById(R.id.closeButton);
        findBox = (EditText) v.findViewById(R.id.findBox);
        findBox.setSingleLine(true);
        findBox.setOnKeyListener(new View.OnKeyListener() {
            @Override

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))) {
                    webView.findAllAsync(findBox.getText().toString());

                    try {
                        // Can't use getMethod() as it's a private method
                        for (Method m : WebView.class.getDeclaredMethods()) {
                            if (m.getName().equals("setFindIsUp")) {
                                m.setAccessible(true);
                                m.invoke(webView, true);
                                break;
                            }
                        }
                    } catch (Exception ignored) {
                    } finally {
                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        // check if no view has focus:
                        View vv = getActivity().getCurrentFocus();
                        if (vv != null) {
                            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                }
                return false;
            }
        });
        nextButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        return v;
    }

    private class DownloadData extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            mMessage = getArguments() != null ? getArguments().getString("message") : "Data not found";
            return null;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                search();
                item.setIcon(R.drawable.ic_favorite_black);
                linearLayout = (ViewGroup) getActivity().findViewById(R.id.layoutId);
                Button bt = new Button(getActivity());
                bt.setText("A Button");
                bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(bt);
                return true;
        }
        return true;
    }

    public void search() {
        container = (LinearLayout) getActivity().findViewById(R.id.layoutId);
        if (flag) {
            container.setVisibility(LinearLayout.VISIBLE);
            flag = false;
        } else {
            container.setVisibility(LinearLayout.GONE);
            flag = true;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            webView.findNext(true);
        } else if (v == closeButton) {
            container.setVisibility(LinearLayout.GONE);
        }
    }
}
