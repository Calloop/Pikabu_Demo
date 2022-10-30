package ru.calloop.pikabu_demo.ui.createPost.adapters;

//import android.annotation.SuppressLint;
//import android.graphics.Color;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.DiffUtil;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import ru.calloop.pikabu_demo.R;
//import ru.calloop.pikabu_demo.databinding.CreatePostImageTypePostBinding;
//import ru.calloop.pikabu_demo.databinding.CreatePostTextTypePostBinding;
//import ru.calloop.pikabu_demo.ui.models.PostItem;
//import ru.calloop.pikabu_demo.ui.models.diffUtils.PostItemsDiffUtil;
//
//public class BlocksListCreatePostAdapter2 extends
//        RecyclerView.Adapter<RecyclerView.ViewHolder> implements
//        ItemMoveCallback.ItemTouchHelperContract {
//
//    private final List<PostItem> postItems;
//    private final StartDragListener startDragListener;
//    private boolean actionModeState;
//
//    // ПЕРЕМЕСТИТЬ В ОБЩИЙ СПИСОК ПЕРЕМЕННЫХ
//    public static final int TYPE_TEXT_BLOCK = 1;
//    public static final int TYPE_IMAGE_BLOCK = 2;
//
//    public BlocksListCreatePostAdapter2(boolean actionModeState,
//                                        StartDragListener startDragListener) {
//        this.actionModeState = actionModeState;
//        this.startDragListener = startDragListener;
//        postItems = new ArrayList<>(1);
//    }
//
//    // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        switch (viewType) {
//            case TYPE_TEXT_BLOCK:
//                CreatePostTextTypePostBinding textBinding =
//                        CreatePostTextTypePostBinding
//                                .inflate(inflater, parent, false);
//                return new TextViewHolder(textBinding);
//            case TYPE_IMAGE_BLOCK:
//                CreatePostImageTypePostBinding imageBinding =
//                        CreatePostImageTypePostBinding
//                                .inflate(inflater, parent, false);
//                return new ImageViewHolder(imageBinding);
//            default:
//                throw new RuntimeException();
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        switch (holder.getItemViewType()) {
//            case TYPE_TEXT_BLOCK:
//                TextViewHolder textViewHolder = (TextViewHolder) holder;
//                textViewHolder.binding.createPostTextTypeContent
//                        .setText(postItems.get(position).getValue());
//
//                textViewHolder.binding.createPostTextTypeContent
//                        .setEnabled(!actionModeState);
//
//                textViewHolder.binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost
//                        .setVisibility(actionModeState ? View.GONE : View.VISIBLE);
//                textViewHolder.binding.includeCreatePostEditingMenu.editingMenuCreatePost
//                        .setVisibility(actionModeState ? View.VISIBLE : View.GONE);
//                break;
//            case TYPE_IMAGE_BLOCK:
//                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
//
//                if (actionModeState) {
//                    imageViewHolder.binding.createPostImageTypeContent
//                            .getLayoutParams().height = 0;
//                    imageViewHolder.binding.createPostImageTypeContent
//                            .setPadding(0, 100, 0, 100);
//                }
//
//                Picasso.get()
//                        .load("file://" + postItems.get(position).getValue())
//                        .placeholder(R.drawable.image_placeholder)
//                        .into(imageViewHolder.binding.createPostImageTypeContent);
//
//                imageViewHolder.binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost
//                        .setVisibility(actionModeState ? View.GONE : View.VISIBLE);
//                imageViewHolder.binding.includeCreatePostEditingMenu.editingMenuCreatePost
//                        .setVisibility(actionModeState ? View.VISIBLE : View.GONE);
//                break;
//        }
//    }
//
//    //region [VIEW HOLDERS]
//    abstract static class BaseViewHolder extends RecyclerView.ViewHolder {
////            RecyclerView.ViewHolder(itemView)
////        abstract fun bind(item: T)
//        public BaseViewHolder(View itemView) {
//            super(itemView);
//
//        }
//    }
//
//    public class TextViewHolder extends RecyclerView.ViewHolder {
//        private final CreatePostTextTypePostBinding binding;
//        private final CreatePostListener listener = new CreatePostListener();
//
//        public TextViewHolder(@NonNull CreatePostTextTypePostBinding textBinding) {
//            super(textBinding.getRoot());
//            binding = textBinding;
//            setListeners();
//        }
//
//        // Можно создать класс TouchableButton с методами onTouchEvent и performClick,
//        // но в них ничего не будет написано
//        @SuppressLint("ClickableViewAccessibility")
//        private void setListeners() {
//            binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2
//                    .setOnClickListener(v -> removePostItem(getAdapterPosition()));
//
//            binding.includeCreatePostEditingMenu.buttonReplaceTextBlockCreatePost2
//                    .setOnClickListener(v ->
//                            startDragListener.requestDrag(TextViewHolder.this));
//
//            binding.includeCreatePostEditingMenu
//                    .buttonReplaceTextBlockCreatePost2.setOnTouchListener((v, event) -> {
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            startDragListener.requestDrag(TextViewHolder.this);
//                            return true;
//                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                            v.performClick();
//                        }
//                        return false;
//                    });
//
//            binding.createPostTextTypeContent.setOnFocusChangeListener((v, hasFocus) -> {
//                if (hasFocus) {
//                    binding.createPostTextTypeContent.addTextChangedListener(listener);
//                    listener.updatePosition(getAdapterPosition());
//                } else {
//                    binding.createPostTextTypeContent.removeTextChangedListener(listener);
//                }
//            });
//        }
//    }
//
//    public class ImageViewHolder extends RecyclerView.ViewHolder {
//        private final CreatePostImageTypePostBinding binding;
//
//        public ImageViewHolder(@NonNull CreatePostImageTypePostBinding imageBinding) {
//            super(imageBinding.getRoot());
//            binding = imageBinding;
//            setListeners();
//        }
//
//        private void setListeners() {
//            binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2
//                    .setOnClickListener(v -> removePostItem(getAdapterPosition()));
//        }
//    }
//    //endregion
//
//    //region [ITEM DATA]
//    @Override
//    public int getItemViewType(int position) {
//        return postItems.get(position).getType();
//    }
//
//    @Override
//    public int getItemCount() {
//        return postItems.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return postItems.get(position).getId();
//    }
//    //endregion
//
//    //region [ITEM MOVEMENT]
//    @Override
//    public void onRowMoved(int fromPosition, int toPosition) {
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(postItems, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(postItems, i, i - 1);
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition);
//    }
//
//    @Override
//    public void onRowSelected(@NonNull TextViewHolder textViewHolder) {
//        textViewHolder.itemView.setBackgroundColor(Color.GRAY);
//    }
//
//    @Override
//    public void onRowSelected(@NonNull ImageViewHolder imageViewHolder) {
//        imageViewHolder.itemView.setBackgroundColor(Color.GRAY);
//    }
//
//    @Override
//    public void onRowClear(@NonNull TextViewHolder textViewHolder) {
//        textViewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
//    }
//
//    @Override
//    public void onRowClear(@NonNull ImageViewHolder imageViewHolder) {
//        imageViewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
//    }
//    //endregion
//
//    //region [POST ITEM OPERATIONS]
//    public boolean createPostItem(int type, String postItemValue) {
//        PostItem postItem = new PostItem(0, type, postItemValue);
//        postItems.add(postItem);
//        notifyItemInserted(postItems.size() - 1);
//        return true;
//    }
//
//    public void removePostItem(int position) {
//        postItems.remove(position);
//        notifyItemRemoved(position);
//    }
//    //endregion
//
//    //region [ADAPTER LIST OPERATIONS]
//    public List<PostItem> getAdapterList() {
//        return postItems;
//    }
//
//    public void setAdapterList(List<PostItem> newPostItems) {
//        PostItemsDiffUtil postItemsDiffUtilCallback =
//                new PostItemsDiffUtil(postItems, newPostItems);
//        DiffUtil.DiffResult postItemsDiffResult =
//                DiffUtil.calculateDiff(postItemsDiffUtilCallback);
//
//        postItems.clear();
//        postItems.addAll(newPostItems);
//        postItemsDiffResult.dispatchUpdatesTo(this);
//    }
//    //endregion
//
//    public void setActionModeState(boolean actionModeState) {
//        this.actionModeState = actionModeState;
//        notifyItemRangeChanged(0, postItems.size());
//    }
//
//    // СДЕЛАТЬ ОТДЕЛЬНЫЙ КЛАСС
//    public class CreatePostListener implements TextWatcher {
//        private int position;
//        private Timer timer;
//
//        public void updatePosition(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//            if (timer != null) {
//                timer.cancel();
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            timer = new Timer();
//            int DELAY = 250;
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    postItems.get(position).setValue(editable.toString());
//                }
//            }, DELAY);
//        }
//    }
//}