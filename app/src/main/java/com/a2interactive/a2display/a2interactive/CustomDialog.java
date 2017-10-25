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
                "Je m'appelle ???, je suis un IA \n" +
                "Je m'entraine à trouver le meilleur \n" +
                "contenu pour vous, celui qui peut\n" +
                "le plus vous intéresser !\n\n" +
                "Cliquez, puis mettez au centre de la\n" +
                "caméra en haut à droite\n" +
                "et je vous montre\n";
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
        String content="Merci d'avoir participé, je m'améliore grâce à vous\n Vous voulez-vous savoir comment A2Display et moi faisons ?";
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
        String content="Merci d'avoir participé, je m'améliore grâce à vous\n Vous voulez-vous savoir comment A2Display et moi faisons ?";
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
        String content="Merci d'avoir participé, je m'améliore grâce à vous\n Vous voulez-vous savoir comment A2Display et moi faisons ?";
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
        String title="blabnla sur comment avec en explication Innovation";
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
        String title="Autre blabla sur ce sur quoi ça peut servir !";
        title_text.setText(title);

        MaterialFancyButton btnQuit = (MaterialFancyButton) dialog.findViewById(R.id.d8_btn_quit);
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
