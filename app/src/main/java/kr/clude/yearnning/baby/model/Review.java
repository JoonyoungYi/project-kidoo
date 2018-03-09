package kr.clude.yearnning.baby.model;

import kr.clude.yearnning.baby.R;

/**
 * Created by yearnning on 15. 2. 24..
 */
public class Review {

    public int star = 0;
    public int img_resource = -1;
    public String content = null;
    public Person person = null;

    public static Review newInstanceById(int id, Chat.Type type) {

        Review review = new Review();
        review.person = PersonParent.newInstanceById(id);

        if (type == Chat.Type.PARENT) {

            switch (id) {

                case 1:
                    review.content = "승협이가 너무 좋아하네요…^^\n";
                    break;

                case 2:
                    review.content = "다음에는 동생도 같이 쳤으면 해요!";
                    break;

                case 3:
                    review.content = "감사합니다. 다음 기회에도 또 오셨으면 좋겠어요";
                    break;

                default:
                    review.content = "감사합니다. 다음에 또 기회되면 연락드릴게요!";
                    break;

            }


        } else {

            review.person = PersonSitter.newInstanceById(1);

            switch (id) {

                case 1:
                    review.content = "오늘 승협이와 놀면서 색찰흙 놀이하면서 찍은 사진입니다..^^\n";
                    review.img_resource = R.drawable.review_sitter_1;
                    break;

                case 2:
                    review.content = "오늘은 현지랑 동생이랑 같이 피아노를 쳤어요. 현지가 피아노를 배운지 얼마 안되었는데도 잘 쳐서 기분이 좋네요.";
                    review.img_resource = R.drawable.review_sitter_2;
                    break;

                case 3:
                    review.content = "오늘은 경준이와 함께 레고 조립을 했습니다. 레고를 처음 해보는 것이라 하여 도와주려고 했었는데, 너무나 재미있어하며 혼자서 해보겠다고 하더군요. 블럭들을 찾아가며 열심히 집중하여 조립하더니, 결국에는 완성하고 나서는 정말 뿌듯해 하더군요. 사진은 처음에 설명서를 보더니 스스로 부품들을 열심히 분류하고 있는 경준이의 모습입니다. 하나를 완성하고 나서 다른 것도 더 만들어 보고 싶다고 하였는데, 제가 가져온 것이 하나 뿐이라 다음에 기회가 된다면 좀 더 복잡하고 큰 레고를 함께 조립해보기로 하였습니다.";
                    review.img_resource = R.drawable.review_sitter_3;
                    break;

                default:
                    review.content = "종찬이랑 한글 수업을 진행했어요~ 자음과 모음을 가르쳤는데, 아직 자음까지 밖에 못배운 것 같네요.. 기회가 되면 모음까지 가르치고 싶어요!";
                    break;

            }


        }

        return review;
    }
}
