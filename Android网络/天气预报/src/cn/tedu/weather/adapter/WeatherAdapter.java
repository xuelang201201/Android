package cn.tedu.weather.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.tedu.weather.R;
import cn.tedu.weather.entity.MyWeather;
import cn.tedu.weather.entity.MyWeather.Result.Data.Weather;
import cn.tedu.weather.entity.Weathers;

public class WeatherAdapter extends BaseAdapter {

	private Context context;
	private MyWeather weather;
	private List<Weathers> weathers;
	private LayoutInflater inflater;

	public WeatherAdapter(Context context, List<Weathers> weathers) {
		super();
		this.context = context;
		this.weathers = weathers;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return weathers.size();
	}

	@Override
	public Weather getItem(int position) {
		return getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.weather_list_item, null);
			holder = new ViewHolder();
			holder.weekDay = (TextView) convertView.findViewById(R.id.tv_list_item_time);
			holder.info = (TextView) convertView.findViewById(R.id.tv_list_item_weather);
			holder.temperature = (TextView) convertView.findViewById(R.id.tv_list_item_temperature);
			holder.wind = (TextView) convertView.findViewById(R.id.tv_list_item_wind);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.info.setText(weather.getResult().getData().getRealtime().getWeather().getInfo());
		holder.weekDay.setText(weather.getResult().getData().getRealtime().getWeek());
		holder.temperature.setText(""+weather.getResult().getData().getRealtime().getWeather());
		holder.wind.setText(""+weather.getResult().getData().getRealtime().getWind());
		
		return convertView;
	}

	class ViewHolder {
		TextView weekDay;
		TextView info;
		TextView temperature;
		TextView wind;
	}

}
