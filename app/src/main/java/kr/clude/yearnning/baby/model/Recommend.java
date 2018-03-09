package kr.clude.yearnning.baby.model;

import kr.clude.yearnning.baby.R;

/**
 * Created by yearnning on 15. 2. 24..
 */
public class Recommend {

    public int profile_img_resource = -1;
    public int img_resource = -1;
    public String name = null;
    public String job = null;


    public static Recommend newInstanceById(int id) {

        Recommend recommend = new Recommend();

        switch (id) {
            case 1:
                recommend.profile_img_resource = R.drawable.recommend_professor_1;
                recommend.img_resource = R.drawable.recommend_1;
                recommend.name = "서교수";
                recommend.job = "이화여대 유아교육과 교수";
                break;
            default:
                recommend.profile_img_resource = R.drawable.recommend_professor_2;
                recommend.img_resource = R.drawable.recommend_2;
                recommend.name = "김교수";
                recommend.job = "숙명여대 유아교육과 교수";
                break;
        }

        return recommend;
    }

}
