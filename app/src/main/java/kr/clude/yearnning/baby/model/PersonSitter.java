package kr.clude.yearnning.baby.model;

import java.util.ArrayList;
import java.util.Random;

import kr.clude.yearnning.baby.R;

/**
 * Created by yearnning on 15. 2. 24..
 */
public class PersonSitter extends Person {

    public int age = -1;
    public int pay_per_hour = -1;
    public int distance_km = -1;
    public int img_resource_bg = -1;
    //
    public String introduce_short = null;
    public String introduce_detail = null;

    private int[] stars = {0, 0, 0, 0, 0};

    public int reviews_parent_cnt = -1;
    public ArrayList<Review> reviews_parent = new ArrayList<>();

    public ArrayList<Label> labels = new ArrayList<>();


    /**
     * @param person_sitter_id
     * @return
     */
    public static PersonSitter newInstanceById(int person_sitter_id) {

        PersonSitter person_sitter = new PersonSitter();
        person_sitter.reviews_parent_cnt = (new Random()).nextInt(10) + 1;
        /**
         *  reviews_sitter.add(PersonSitter.newInstance("이태연", 25, 1, 10000, R.drawable.sitter_0));
         *  reviews_sitter.add(PersonSitter.newInstance("박하니", 24, 1, 10000, R.drawable.sitter_1));
         *  reviews_sitter.add(PersonSitter.newInstance("김지은", 21, 1, 10000, R.drawable.sitter_2));
         */
        person_sitter.id = person_sitter_id;

        switch (person_sitter_id) {

            case 1:
                person_sitter.age = 24;
                person_sitter.name = "이태연";
                person_sitter.distance_km = 1;
                person_sitter.img_resource = R.drawable.person_sitter_1;
                person_sitter.img_resource_bg = R.drawable.person_sitter_1_bg;
                person_sitter.pay_per_hour = 10000;
                break;

            case 2:
                person_sitter.age = 25;
                person_sitter.name = "박하니";
                person_sitter.distance_km = 2;
                person_sitter.img_resource = R.drawable.person_sitter_2;
                person_sitter.img_resource_bg = R.drawable.person_sitter_2_bg;
                person_sitter.pay_per_hour = 7000;
                break;

            case 3:
                person_sitter.age = 23;
                person_sitter.name = "김지은";
                person_sitter.distance_km = 5;
                person_sitter.img_resource = R.drawable.person_sitter_3;
                person_sitter.img_resource_bg = R.drawable.person_sitter_3_bg;
                person_sitter.pay_per_hour = 12000;
                break;

            default:
                person_sitter.age = 24;
                person_sitter.name = "이태연";
                person_sitter.distance_km = 1;
                person_sitter.img_resource = R.drawable.person_sitter_1;
                person_sitter.img_resource_bg = R.drawable.person_sitter_1_bg;
                person_sitter.pay_per_hour = 10000;
                break;
        }

        return person_sitter;
    }
}
