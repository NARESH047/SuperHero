package com.example.superhero;

//public class HeroAdapter extends BaseAdapter implements Filterable {
//    SuperHero currentSuperHero;
//    View listItemView;
//    private List<SuperHero> superHeroList;
//    private List<SuperHero> superHeroListFiltered;
//    private Context context;
//
////    public HeroAdapter(Context context, List<SuperHero> superHeroes) {
////        super(context, 0, superHeroes);
////    }
//
//    @Override
//    public int getCount() {
//        return superHeroListFiltered.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        View view = getLayoutInflater().inflate(R.layout.row_items,null);
//
//
//        TextView powerView = (TextView) listItemView.findViewById(R.id.power);
//
//        GradientDrawable powerCircle = (GradientDrawable) powerView.getBackground();
//        int powerColor = getpowerColor(superHeroListFiltered.get(position).getPower());
//        powerView = (TextView) listItemView.findViewById(R.id.power);
//        powerView.setText(String.valueOf(superHeroListFiltered.get(position).getPower()));
//
//        powerCircle.setColor(powerColor);
//
//
//        TextView name = (TextView) listItemView.findViewById(R.id.name);
//
//        name.setText(superHeroListFiltered.get(position).getName());
//
//        TextView family = (TextView) listItemView.findViewById(R.id.family);
//        family.setText(superHeroListFiltered.get(position).getRace());
//
//
//        TextView fullName = (TextView) listItemView.findViewById(R.id.full_name);
//
//        fullName.setText(superHeroListFiltered.get(position).getFullName());
//
//        TextView gender = (TextView) listItemView.findViewById(R.id.gender);
//        if(superHeroListFiltered.get(position).getGender() != null){
//            gender.setText(superHeroListFiltered.get(position).getGender());
//        }
//
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ItemsPreviewActivity.class).putExtra("items", itemsModelListFiltered.get(position)));
//            }
//        });
//
//
//
//        return view;
//    }
//
//    @NonNull
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                return null;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//
//
//            }
//        }
//        return super.getFilter();
//    }
//
//
//
//
//
//
//    private int getpowerColor(double power) {
//        int powerColorResourceId = 0;
//        int powerFloor = (int) (power);
//        if(powerFloor<=10) {
//            powerColorResourceId = R.color.power1;
//        }
//        else if(powerFloor<=20  ) {
//            powerColorResourceId = R.color.power2;
//        }
//        else if(powerFloor<=40 ) {
//            powerColorResourceId = R.color.power3;
//        }
//        else if(powerFloor<=50 ) {
//            powerColorResourceId = R.color.power4;
//        }
//        else if(powerFloor<=60  ) {
//            powerColorResourceId = R.color.power5;
//        }
//        else if(powerFloor<=70  ) {
//            powerColorResourceId = R.color.power6;
//        }
//        else if(powerFloor<=80  ) {
//            powerColorResourceId = R.color.power7;
//        }
//        else if(powerFloor<=90  ) {
//            powerColorResourceId = R.color.power8;
//        }
//        else if(powerFloor<=95  ) {
//            powerColorResourceId = R.color.power9;
//        }
//        else if(powerFloor<=100  ) {
//            powerColorResourceId = R.color.power10plus;
//        }
//
//        return ContextCompat.getColor(getContext(), powerColorResourceId);
//    }
//
//
//}

