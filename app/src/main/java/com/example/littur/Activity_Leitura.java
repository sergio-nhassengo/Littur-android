package com.example.littur;

import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Leitura extends AppCompatActivity {
    private Leitura leitura;
    TabLayout MyTabs;
    ViewPager MyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leitura = new Leitura();
        leitura = (Leitura) getIntent().getSerializableExtra("key");
        Holder_Leitura h = new Holder_Leitura(leitura);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.layout_leitura_portrait);

            MyTabs = (TabLayout) findViewById(R.id.MyTabs);
            MyPage = (ViewPager) findViewById(R.id.MyPage);

            SetUpViewPager(MyPage);
            MyTabs.setupWithViewPager(MyPage);
        }else{
            setContentView(R.layout.layout_leitura_landscape);
            TextView portugues = (TextView)findViewById(R.id.textPortugues);
            TextView ronga = (TextView)findViewById(R.id.textRonga);
            TextView livroPt = (TextView)findViewById(R.id.livroPt);
            TextView livroRonga = (TextView)findViewById(R.id.livroRg);

            livroPt.setText(leitura.getLivro());
            portugues.setText(leitura.gettexto());
            livroRonga.setText(leitura.getLivro());
            ronga.setText(leitura.getTraducao());
        }
    }

    public  void SetUpViewPager(ViewPager viewPage){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());

        Frag_Leitura_Portugues lpt = new Frag_Leitura_Portugues();
        Frag_Leitura_Ronga lrg = new Frag_Leitura_Ronga();

        Adapter.AddFragmentPage(lpt, "Portugues");
        Adapter.AddFragmentPage(lrg, "Ronga");
        viewPage.setAdapter(Adapter);
    }

    public class MyViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> MyFragment = new ArrayList<>();
        private List<String> MyPageTittle = new ArrayList<>();

        public MyViewPageAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void AddFragmentPage(android.support.v4.app.Fragment frag, String tittle){
            MyFragment.add(frag);
            MyPageTittle.add(tittle);
        }

        public CharSequence getPageTitle(int position){
            return MyPageTittle.get(position);
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return MyFragment.get(position);
        }
    }
}
