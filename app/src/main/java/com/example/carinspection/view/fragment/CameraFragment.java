package com.example.carinspection.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.example.carinspection.R;
import com.example.carinspection.databinding.FragmentCameraBinding;
import com.example.carinspection.model.InspectionData;
import com.example.carinspection.model.UploadMediaData;
import com.example.carinspection.util.AppHelper;
import com.example.carinspection.util.AutoFitTextureView;
import com.example.carinspection.util.Constants;
import com.example.carinspection.util.SimpleCountDownTimer;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CameraFragment extends CameraVideoFragment implements SimpleCountDownTimer.OnCountDownListener{


    TextView tv_counter;
    private static final int MAX_VIDEO_DURATION = 8 * 1000;
    private static final int ID_TIME_COUNT = 0x1006;
    Unbinder unbinder;
    private String mOutputFilePath;
    private FragmentCameraBinding binding = null;
    private final SimpleCountDownTimer simpleCountDownTimer = new SimpleCountDownTimer(0, 60, 1, this);
    private String documentType;
    private String id;
    private String idType;
    private int screenNumber;
    private String objectType;
    private OnFragmentInteractionListener mListener;
    private InspectionData inspectionData;
    private UploadMediaData uploadMediaData;

    public CameraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */


    public static CameraFragment newInstance(String documentType,String id,String idType,int screenNumber,String objectType) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putString(Constants.DOCUMENT_TYPE,documentType);
        args.putString(Constants.ID,documentType);
        args.putString(Constants.ID_TYPE,documentType);
        args.putInt(Constants.SCREEN_NUMBER,screenNumber);
        args.putString(Constants.OBJECT_TYPE,objectType);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            documentType = bundle.getString(Constants.DOCUMENT_TYPE);
            id = bundle.getString(Constants.ID);
            idType = bundle.getString(Constants.ID_TYPE);
            screenNumber = bundle.getInt(Constants.SCREEN_NUMBER);
            objectType = bundle.getString(Constants.OBJECT_TYPE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentCameraBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.mRecordVideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mIsRecordingVideo) {
                    try {
                        binding.group.setVisibility(View.GONE);
                        stopRecordingVideo();
                        prepareViews();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                  startRecording();
                }
            }
        });
        binding.mPlayVideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                binding.mVideoView.start();
                binding.mPlayVideo.setVisibility(View.GONE);
            }
            });
      /*  binding.Upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                binding.mVideoView.start();
                binding.mPlayVideo.setVisibility(View.GONE);

            }
        });*/
        binding.ReTake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {
                    File fdelete = new File(mOutputFilePath);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            binding.mVideoView.setVisibility(View.GONE) ;
                            binding.group.setVisibility(View.GONE) ;
                            startRecording();
                        } else {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startRecording() {
        startRecordingVideo();
        binding.tvCounter.setVisibility(View.VISIBLE);
        binding.tvCounter.setText("00:0" + (MAX_VIDEO_DURATION / 1000));
        simpleCountDownTimer.start(false);
                  /*  Message msg = mHandler.obtainMessage(ID_TIME_COUNT, 1,
                            MAX_VIDEO_DURATION / 1000);
                    mHandler.sendMessage(msg);*/
        binding.mRecordVideo.setImageResource(R.drawable.ic_stop);
        //Receive out put file here
        mOutputFilePath = getCurrentFile().getAbsolutePath();
    }

    @Override
    public int getTextureResource() {
        return R.id.mTextureView;
    }



    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case ID_TIME_COUNT:
                    if (mIsRecordingVideo) {
                        if (msg.arg1 > msg.arg2) {
                            // mTvTimeCount.setVisibility(View.INVISIBLE);
                            tv_counter.setText("00:00");
                            try {
                                stopRecordingVideo() ;
                                prepareViews();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            tv_counter.setText("00:0" + (msg.arg2 - msg.arg1));
                            Message msg2 = mHandler.obtainMessage(ID_TIME_COUNT,
                                    msg.arg1 + 1, msg.arg2);
                            mHandler.sendMessageDelayed(msg2, 1000);
                        }
                    }
                    break;

                default:
                    break;
            }
        }

        ;

    };

    private void prepareViews() {
        if (binding.mVideoView.getVisibility() == View.GONE) {
            binding.mVideoView.setVisibility(View.VISIBLE);
            binding.mPlayVideo.setVisibility(View.VISIBLE);
            binding.mTextureView.setVisibility(View.GONE);
            binding.group.setVisibility(View.VISIBLE);
            binding.tvCounter.setVisibility(View.GONE);
            setMediaForRecordVideo();
        }
    }

    private void setMediaForRecordVideo() {
        // Set media controller
        binding.mVideoView.setMediaController(new MediaController(getActivity()));
        binding.mVideoView.requestFocus();
        binding.mVideoView.setVideoPath(mOutputFilePath);
        binding.mVideoView.seekTo(100);
        binding.mVideoView.setOnCompletionListener(mp -> {
            // Reset player
            binding.mVideoView.setVisibility(View.GONE);
            binding.mTextureView.setVisibility(View.VISIBLE);
            binding.mPlayVideo.setVisibility(View.GONE);
            binding.mRecordVideo.setImageResource(R.drawable.ic_record);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCountDownActive(String time) {
        binding.tvCounter.post(() -> binding.tvCounter.setText(time));
    }

    @Override
    public void onCountDownFinished() {
        try {
            stopRecordingVideo() ;
            prepareViews();
            binding.group.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentCallMethodtoActivity");
        }
    }

    public interface OnFragmentInteractionListener {
        void  sendDataToActivity(InspectionData inspectionData);
    }
   public void getData() {
        if (checkValidationManadatory()) {
            uploadMediaData = new UploadMediaData(mOutputFilePath,Constants.VIDEO,documentType, Calendar.getInstance().getTime().toString());
            mListener.sendDataToActivity(inspectionData);
        }

    }
    private Boolean checkValidationManadatory() {
        inspectionData = new InspectionData(AppHelper.convertToString(uploadMediaData), screenNumber, Constants.UPLOAD_IMAGE, false,0);
        return true;
    }

}