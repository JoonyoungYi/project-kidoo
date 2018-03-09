package kr.clude.yearnning.baby.model;

import java.util.ArrayList;
import java.util.Random;

import kr.clude.yearnning.baby.R;

/**
 * Created by yearnning on 15. 2. 24..
 */
public class PersonParent extends Person {

    public ArrayList<Review> reviews_sitter = new ArrayList<>();

    /**
     * @param person_sitter_id
     * @return
     */
    public static PersonParent newInstanceById(int person_sitter_id) {

        PersonParent person_parent = new PersonParent();

        /**
         *  reviews_sitter.add(PersonSitter.newInstance("이태연", 25, 1, 10000, R.drawable.sitter_0));
         *  reviews_sitter.add(PersonSitter.newInstance("박하니", 24, 1, 10000, R.drawable.sitter_1));
         *  reviews_sitter.add(PersonSitter.newInstance("김지은", 21, 1, 10000, R.drawable.sitter_2));
         */
        person_parent.id = person_sitter_id;

        switch (person_sitter_id) {

            case 1:
                person_parent.name = "박정현";
                person_parent.img_resource = R.drawable.person_parent_1;
                break;

            case 2:
                person_parent.name = "이정화";
                person_parent.img_resource = R.drawable.person_parent_2;
                break;

            case 3:
                person_parent.name = "최성희";
                person_parent.img_resource = R.drawable.person_parent_3;
                break;

            default:
                person_parent.name = "석현정";
                person_parent.img_resource = R.drawable.person_parent_4;
                break;
        }

        return person_parent;
    }
}
