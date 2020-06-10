package com.example.androidclass.plant_des;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidclass.R;
import com.example.androidclass.plant_grid.PlantGridActivity;
import com.example.androidclass.plant_list.PlantListActivity;

public class PlantGridDesActivity extends AppCompatActivity {
    TextView plantTitle1,plantDes1;
    String[] desc = new String[] {"多年生直立草本，通常高约20-30(60）厘米，每年由地下茎上端接近地面的几个节上生出数条更新枝成为地上茎。地下茎分枝多，直径约2-3毫米，节上有一红褐至褐色鳞片，鳞片卵形而基部抱茎。地上茎细弱（常较地下茎为细），不分枝或分枝，茎枝基部无芽鳞，沿节多少呈之字形曲折，略有棱或在上部节间兼有较为明显的沟，枝上部的棱上有稀少硬毛，被细小钙质颗粒。叶宽卵形或倒卵形，长(2)4-6（10）厘米，宽(0.8)2-3(5.3)厘米，枝两端者较小，先端渐尖或偶而钝圆，基部骤窄而后渐狭或仅为渐狭，除边缘外两面无毛或近无毛，常有细小钙质颗粒。花序生于枝端和上部1-3节叶腋的短柄上，基部紧托有1片披针形至长圆形的叶，含（1-5）15-30枚或更多的花，花期中经常有1-5花开放；苞片长6.5-8毫米，宽3-3.5毫米，长卵形，先端渐尖成一短细尖，小苞长8-9.5毫米，宽3-3.5毫米，狭长圆形至狭长卵形，先端有细尖；萼长（12）13-15（18）毫米，中部直径约1.5-2毫米，沿脉有稀少长硬毛，裂片长约2毫米；花冠长25-28毫米，筒部紫红色，裂片蓝色，倒三角形，长8毫米，先端宽达8毫米，顶缘浅凹而沿中脉伸出一窄三角形的短尖；花丝略伸于花冠喉部之外，花药长约2毫米，蓝色；子房椭圆形，花柱异长，短柱型的柱头不外露，长柱型的柱头伸于花药之上。蒴果椭圆状卵形，淡黄褐色，长约6毫米；种子红褐色，粗糙，有棱，先端约1/3渐细成喙。花期7-9月，果期8-10月。",
            "长寿花是水仙属的种类之一。植株很修长，株高可达40厘米，鳞茎较小，叶鲜绿色，狭线形，表面有凹沟。花茎等长于叶，花顶生2－6朵，花瓣和副花冠均为金黄色，花瓣具短尖，副花冠边缘波状，花芬芳，直径达3厘米，副花冠仅高4毫米，直径约1厘米。原仅分布于伊比利亚半岛，但较早时期就常用作园艺植物，因此逸生于法国南部、意大利和达尔马提亚。中国引种栽培供观赏。",
            "风车茉莉其实就是络石藤，五月开花，形如万字，故又称万字茉莉。原产黄河以南地区，属于夹竹桃科常绿攀缘藤本植物。单叶对生在新枝上，椭圆形或卵圆形，长4厘米至5厘米，宽1．5厘米至2厘米。风车茉莉第一轮新叶粉红色，每枝多数为一对，少数2-3对粉红叶，第二对到第三对为纯白色叶从新叶到老叶白色成份逐渐减少。",
            "玛格丽特花，原名：木茼蒿，又名木春菊、法兰西菊,原产北非加那利群岛。菊科，木茼蒿属灌木，高达1米，枝条大部木质化。 玛格丽特花叶宽卵形、椭圆形或长椭圆形，二回羽状分裂。一回为深裂或几全裂，二回为浅裂或半裂。舌状花瘦果有3条具白色膜质宽翅形的肋。两性花瘦果有1-2条具狭翅的肋，并有4-6条细间肋。冠状冠毛长0.4毫米。花果期2-10月。盆栽观赏，或作背景绿叶材料布置。",
            "月季花：被称为花中皇后，又称“月月红”，是常绿、半常绿低矮灌木，四季开花﹐一般为红色﹐或粉色、偶有白色和黄色﹐可作为观赏植物，也可作为药用植物，亦称月季。有三个自然变种，现代月季花型多样，有单瓣和重瓣，还有高心卷边等优美花型；其色彩艳丽、丰富，不仅有红、粉黄、白等单色，还有混色、银边等品种；多数品种有芳香。月季的品种繁多，世界上已有近万种，中国也有千种以上。",
            "属双子叶植物纲、茜草科、栀子属常绿灌木，枝叶繁茂，叶色四季常绿，花芳香，是重要的庭院观赏植物。单叶对生或三叶轮生，叶片倒卵形，革质，翠绿有光泽。浆果卵形，黄色或橙色。除观赏外，其花、果实、叶和根可入药，有泻火除烦，清热利尿，凉血解毒之功效。花可做茶之香料，果实可消炎祛热。是优良的芳香花卉。栀子花喜光照充足且通风良好的环境，但忌强光曝晒。宜用疏松肥沃、排水良好的酸性土壤种植。可用扦插、压条、分株或播种繁殖，喜温湿，向阳，较耐寒，耐半阴，怕积水，要求疏松、肥沃和酸性的沙壤土，易发生叶子发黄的黄化病.",
            "又名映山红、山石榴，落叶灌木。相传，古有杜鹃鸟，日夜哀鸣而咯血，染红遍山的花朵，因而得名。杜鹃花一般春季开花，每簇花2-6朵，花冠漏斗形，有红、淡红、杏红、雪青、白色等，花色繁茂艳丽。生于海拔500-1200（-2500）米的山地疏灌丛或松林下，为中国中南及西南典型的酸性土指示植物。该物种全株供药用：有行气活血、补虚，治疗内伤咳嗽，肾虚耳聋，月经不调，风湿等疾病。又因花冠鲜红色，为著名的花卉植物，具有较高的观赏价值，在世界各公园中均有栽培。中国江西、安徽、贵州以杜鹃花为省花，定为市花的城市多达七八个。1985年5月杜鹃花被评为中国十大名花之六。",
            "桂花是中国木犀属众多树木的习称，代表物种木犀（学名：Osmanthus fragrans (Thunb.) Lour.），又名岩桂，系木犀科常绿灌木或小乔木，质坚皮薄，叶长椭圆形面端尖，对生，经冬不凋。花生叶腑间，花冠合瓣四裂，形小，其园艺品种繁多，最具代表性的有金桂、银桂、丹桂、月桂等。桂花是中国传统十大名花之一，集绿化、美化、香化于一体的观赏与实用兼备的优良园林树种，桂花清可绝尘，浓能远溢，堪称一绝。尤其是仲秋时节，丛桂怒放，夜静轮圆之际，把酒赏桂，陈香扑鼻，令人神清气爽。在中国古代的咏花诗词中，咏桂之作的数量也颇为可观。自古就深受中国人的喜爱，被视为传统名花。"};
    int position1=0;
    String TAG="PlantGridDesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_grid_des);
        final String name1=getIntent().getStringExtra("nameStr1");
        position1=getIntent().getIntExtra("position1",0);
        Log.i(TAG,"onCreate:title="+name1);
        Log.i(TAG,"onCreate:rate="+position1);
        plantTitle1=findViewById(R.id.plant_grid_des_title);
        plantTitle1.setText(name1);
        plantDes1=findViewById(R.id.plant_grid_description);
        plantDes1.setText(desc[position1]);
    }

    public void onClick_back(View view) {
        if(view.getId()==R.id.plant_grid_des_back){
            Intent intent1=new Intent(this, PlantGridActivity.class);
            startActivity(intent1);
        }
    }
}


