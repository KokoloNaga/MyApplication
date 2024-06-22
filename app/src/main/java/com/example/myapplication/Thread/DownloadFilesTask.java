package com.example.myapplication.Thread;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadFilesTask extends AsyncTask<String, Integer, Long>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("DownloadFilesTask", "执行任务之前");
    }

    @Override
    protected Long doInBackground(String... strings) {
        int count = strings[0].length();
        long totalSize = 0;
        for(int i = 0;i < count;i++){
            totalSize += i;
            publishProgress(i);  // 执行此方法会调用onProgressUpdate更新下载进度

            if(isCancelled())  // 如果取消即结束任务
                break;
        }
        return totalSize;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Log.i("DownloadFilesTask", "当前下载进度：" + values[0].intValue());
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);

        Log.i("DownloadFilesTask", "下载完成：" + aLong);
    }
}
