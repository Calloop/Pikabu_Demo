package ru.calloop.pikabu_demo.ui.utility.createPost;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.adapters.createPost.holders.BaseViewHolder;

public class ItemMoveCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperContract adapter;

    public ItemMoveCallback(ItemTouchHelperContract adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        adapter.onRowMoved(
                viewHolder.getBindingAdapterPosition(), target.getBindingAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            adapter.onRowSelected((BaseViewHolder) viewHolder);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        adapter.onRowClear((BaseViewHolder) viewHolder);
    }

    public interface ItemTouchHelperContract {
        void onRowMoved(int fromPosition, int toPosition);

        void onRowSelected(BaseViewHolder viewHolder);

        void onRowClear(BaseViewHolder viewHolder);
    }
}