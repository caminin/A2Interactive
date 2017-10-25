package com.a2interactive.a2display.a2interactive;

import android.app.Activity;
import android.app.Dialog;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.affectiva.android.affdex.sdk.detector.Face;
import com.rilixtech.materialfancybutton.MaterialFancyButton;

/**
 * Created by caminin on 23/10/17.
 */

public class CustomDialog {
    private Dialog dialog;
    private EndingTask endingTask;
    private MainActivity activity;
    private Face face;

    public CustomDialog(MainActivity activity) {
        this.activity = activity;
    }

    public void showLaunchDialog(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_0);

        TextView title_text = (TextView) dialog.findViewById(R.id.d0_title);
        String title="Bonjour !\n\n" +
                "Je m'appelle Jarvis, je suis une IA \n" +
                "Je m'entraine à trouver le meilleur \n" +
                "contenu pour vous !\n\n" +
                "Cliquez, puis filmez-vous avec la\n" +
                "caméra en haut à droite\n" +
                "et je vous montre mes talents\n";
        title_text.setText(title);

        MaterialFancyButton btnQuit = (MaterialFancyButton) dialog.findViewById(R.id.d0_btn_quit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                activity.begin();
            }
        });

        dialog.show();
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public void showDialog(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_1);

        TextView title_text = (TextView) dialog.findViewById(R.id.d1_title);
        String title="Bonjour ";
        switch(face.appearance.getGender()){
            case FEMALE:
                title+="Madame";
                break;
            case MALE:
                title+="Monsieur";
                break;
        }
        title_text.setText(title);

        TextView content_text = (TextView) dialog.findViewById(R.id.d1_content);
        String content="Voulez-vous que j'essaie de deviner votre âge ?";
        content_text.setText(content);

        MaterialFancyButton btnYes = (MaterialFancyButton) dialog.findViewById(R.id.d1_btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog2();
            }
        });

        MaterialFancyButton btnNo = (MaterialFancyButton) dialog.findViewById(R.id.d1_btn_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
            }
        });

        dialog.show();
    }

    public void cancelDialog(){
        if(endingTask!=null){
            endingTask.onProgressUpdate(true);
        }
        dialog.cancel();
    }

    public void dialog2(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_2);

        TextView title_text = (TextView) dialog.findViewById(R.id.d2_title);
        String title="Attendez un peu je réfléchis...";
        title_text.setText(title);

        dialog.show();

        new LoadingTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 4L);
    }

    public void dialog2Ending(){
        cancelDialog();
        dialog3();
    }

    public void dialog3(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_3);

        TextView title_text = (TextView) dialog.findViewById(R.id.d3_title);
        String title="Je pense que j'ai trouvé !";
        title_text.setText(title);

        TextView title_content = (TextView) dialog.findViewById(R.id.d3_content);
        String textValue="";
        switch (face.appearance.getAge()) {
            case AGE_UNKNOWN:
                textValue = "Je ne sais pas et Du tout";
                break;
            case AGE_UNDER_18:
                textValue = "pas beaucoup et 18";
                break;
            case AGE_18_24:
                textValue = "18 et 24 ans";
                break;
            case AGE_25_34:
                textValue = "25 et 34 ans";
                break;
            case AGE_35_44:
                textValue = "35 et 44 ans";
                break;
            case AGE_45_54:
                textValue = "45 et 54 ans";
                break;
            case AGE_55_64:
                textValue = "55 et 64 ans";
                break;
            case AGE_65_PLUS:
                textValue = "plus de 65 ans";
                break;
        }
        String content="Je crois que vous avez entre "+textValue;
        title_content.setText(content);

        MaterialFancyButton btnYes = (MaterialFancyButton) dialog.findViewById(R.id.d3_btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog4();
            }
        });

        MaterialFancyButton btnMaybe = (MaterialFancyButton) dialog.findViewById(R.id.d3_btn_maybe);
        btnMaybe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog5();
            }
        });

        MaterialFancyButton btnNo = (MaterialFancyButton) dialog.findViewById(R.id.d3_btn_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog6();
            }
        });

        dialog.show();
    }

    public void dialog4(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_4_5_6);

        MaterialFancyButton btnHow = (MaterialFancyButton) dialog.findViewById(R.id.d456_how);
        btnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog7();
            }
        });

        TextView title_text = (TextView) dialog.findViewById(R.id.d456_title);
        String title="Génial ! Encore une bonne réponse ";
        title_text.setText(title);

        TextView title_content = (TextView) dialog.findViewById(R.id.d456_content);
        String content="Un point de plus pour moi, Merci !\n" +
                "Vous voulez savoir comment je fais ?";
        title_content.setText(content);

        ImageView smiley=(ImageView)dialog.findViewById(R.id.d456_smiley);
        smiley.setImageResource(R.drawable.face_laughing);

        dialog.show();
    }

    public void dialog5(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_4_5_6);

        MaterialFancyButton btnHow = (MaterialFancyButton) dialog.findViewById(R.id.d456_how);
        btnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog7();
            }
        });

        TextView title_text = (TextView) dialog.findViewById(R.id.d456_title);
        String title="Zut !";
        title_text.setText(title);

        TextView title_content = (TextView) dialog.findViewById(R.id.d456_content);
        String content="Ça m'arrive de faire des erreurs, je m'améliore de jour en jour !\n" +
                "Vous voulez savoir comment je fais ?";
        if(face.qualities.getBrightness()<30 || face.qualities.getBrightness()>30){
            content+="\nJ'ai détecté un petit soucis de lumière, j'y arriverai peut-être si je vous vois mieux ?";
        }
        title_content.setText(content);

        ImageView smiley=(ImageView)dialog.findViewById(R.id.d456_smiley);
        smiley.setImageResource(R.drawable.face_smirk);

        dialog.show();
    }

    public void dialog6(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_4_5_6);

        MaterialFancyButton btnHow = (MaterialFancyButton) dialog.findViewById(R.id.d456_how);
        btnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog7();
            }
        });

        TextView title_text = (TextView) dialog.findViewById(R.id.d456_title);
        String title="Mince ! J'espère que je ne me suis pas trompé de beaucoup...";
        title_text.setText(title);

        TextView title_content = (TextView) dialog.findViewById(R.id.d456_content);
        String content="Désolé de cette erreur... Promis j'essaierai de faire mieux !\n" +
                "Vous voulez savoir comment je fais ?";
        if(face.qualities.getBrightness()<30 || face.qualities.getBrightness()>30){
            content+="\nJ'ai détecté un petit soucis de lumière, j'y arriverai peut-être si je vous vois mieux ?";
        }
        title_content.setText(content);

        ImageView smiley=(ImageView)dialog.findViewById(R.id.d456_smiley);
        smiley.setImageResource(R.drawable.face_worried);

        dialog.show();
    }

    public void dialog7(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_7);

        TextView title_text = (TextView) dialog.findViewById(R.id.d7_title);
        String title="Je travaille avec les dernières technologie de reconnaissance visuelle afin d'être le plus pertinent possible." +
                "\n\nPour des raisons d'anonymat, je ne garde aucune donnée ! \n\nJ'essaie simplement de m'améliorer afin " +
                "d'apporter la meilleure expérience possible." +
                "\n\nJ'ai été développé par A2Display, Jeune Entreprise Innovante et French Tech avec l'aide du Laboratoire d'IA sur Angers";
        title_text.setText(title);

        MaterialFancyButton btnNext = (MaterialFancyButton) dialog.findViewById(R.id.d7_btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog8();
            }
        });

        dialog.show();
    }

    public void dialog8(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_8);

        TextView title_text = (TextView) dialog.findViewById(R.id.d8_title);
        String title="Pourquoi m'utiliser ? C'est très simple !\n\n" +
                "Je sais trier les informations à envoyer aux gens, par exemple privilégier les informations locales\n\n" +
                "Je simplifie beaucoup le travail en amont, vous me donnez les documents et je vous aide " +
                "à les afficher correctement aux personnes qui sont intéressées\n\n" +
                "Bref, je suis la nouvelle génération intelligente d'affichage pour communiquer et proposer de l'information pertinente";
        title_text.setText(title);

        MaterialFancyButton btnQuit = (MaterialFancyButton) dialog.findViewById(R.id.d8_btn_quit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                dialog9();
            }
        });

        dialog.show();
    }

    public void dialog9(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_9);

        TextView title_text = (TextView) dialog.findViewById(R.id.d9_title);
        String title="Je me joins à l'équipe A2Display pour vous remercier de votre participation à cette nouvelle expérience technologique !\n\n" +
                "J'espère qu'elle vous a permis d'entrevoir les nouvelles possibilités que j'offre, même si j'ai gardé quelques secrets en réserve\n\n";
        if(face.appearance.getGlasses()==Face.GLASSES.YES){
            title+="(Par exemple je sais que vous avez des lunettes,";
        }
        else{
            title+="(Par exemple je sais que vous n'avez pas de lunette,";
        }
        title+=" que vous l'emoji le plus proche de vous est ";
        switch(face.emojis.getDominantEmoji()){
            case DISAPPOINTED:
                title+="'Déçu'";
                break;
            case FLUSHED:
                title+="'Timide'";
                break;
            case KISSING:
                title+="'Bisous'";
                break;
            case LAUGHING:
                title+="'Rire'";
                break;
            case RAGE:
                title+="'Colère'";
                break;
            case RELAXED:
                title+="'Relaxé'";
                break;
            case SCREAM:
                title+="'Cri'";
                break;
            case SMILEY:
                title+="'Sourire'";
                break;
            case SMIRK:
                title+="'Beurk !'";
                break;
            case STUCK_OUT_TONGUE:
                title+="'Tire la langue'";
                break;
            case STUCK_OUT_TONGUE_WINKING_EYE:
                title+="'Tire la langue'";
                break;
            case WINK:
                title+="'Clin d'oeil'";
                break;
            case UNKNOWN:
                title+="'Normal'";
                break;
        }



        title+=")\n\nVous voulez en savoir plus ? Un mail a retenir : contact@a2display.fr\n\n" +
                "Merci aux Vitrines d'Angers, grâce à eux je vous offre du contenu personnalisé";
        title_text.setText(title);

        MaterialFancyButton btnQuit = (MaterialFancyButton) dialog.findViewById(R.id.d9_btn_quit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
                activity.google_vision();
            }
        });

        dialog.show();
    }

    private class EndingTask extends AsyncTask<Long, Boolean, Void> {
        private boolean is_already_ended=false;
        @Override
        protected Void doInBackground(Long... params) {
            long start=System.nanoTime();
            long endTime=params[0];
            while((System.nanoTime()-start)<(endTime*1000000000));
            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.d("Task","Request: Detecting in image ");
        }

        @Override
        protected void onProgressUpdate(Boolean... progress) {
            is_already_ended=progress[0];
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("Tracker","Cela fait longtemps que j'attends");
            if(is_already_ended == false){
                cancelDialog();
                activity.google_vision();
            }
        }
    }

    private class LoadingTask extends AsyncTask<Long, Boolean, Void> {
        private boolean is_already_ended=false;
        @Override
        protected Void doInBackground(Long... params) {
            long start=System.nanoTime();
            long endTime=params[0];
            while((System.nanoTime()-start)<(endTime*1000000000));
            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.d("Task","Request: Detecting in image ");
        }

        @Override
        protected void onProgressUpdate(Boolean... progress) {
            is_already_ended=progress[0];
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("Tracker","Cela fait 10s que j'attends");
            if(is_already_ended == false){
                dialog2Ending();
            }
        }
    }
}
