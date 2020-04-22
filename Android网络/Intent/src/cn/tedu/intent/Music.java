package cn.tedu.intent;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {
	public String path;
	public String name;
	public long duration;

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(path);
		dest.writeLong(duration);
	}
	
	public static final Parcelable.Creator<Music> CREATOR = new Creator<Music>() {

		@Override
		public Music[] newArray(int size) {
			return new Music[size];
		}
		
		@Override
		public Music createFromParcel(Parcel source) {
			Music music = new Music();
			music.name = source.readString();
			music.path = source.readString();
			music.duration = source.readLong();
			return music;
		}
	};
	
	@Override
	public String toString() {
		return "Music [path=" + path + ", name=" + name + ", duration="
				+ duration + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
