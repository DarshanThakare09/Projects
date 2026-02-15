package com.example.hotelmanagment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RoomManager manager;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems;
    private Button btnAddRoom, btnNewBooking, btnCheckout, btnWaiting, btnActions, btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new RoomManager();
        // seed with some rooms
        manager.addRoom("101","Single", 999);
        manager.addRoom("102","Double", 1499);
        manager.addRoom("103","Single", 899);

        listView = findViewById(R.id.roomListView);
        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        btnAddRoom = findViewById(R.id.btnAddRoom);
        btnNewBooking = findViewById(R.id.btnNewBooking);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnWaiting = findViewById(R.id.btnWaitingList);
        btnActions = findViewById(R.id.btnActions);
        btnRefresh = findViewById(R.id.btnRefresh);

        refreshRoomList();

        btnAddRoom.setOnClickListener(v -> showAddRoomDialog());
        btnNewBooking.setOnClickListener(v -> showNewBookingDialog());
        btnCheckout.setOnClickListener(v -> showCheckoutDialog());
        btnWaiting.setOnClickListener(v -> showWaitingListDialog());
        btnActions.setOnClickListener(v -> showActionsDialog());
        btnRefresh.setOnClickListener(v -> refreshRoomList());
    }

    private void refreshRoomList() {
        List<Room> rooms = manager.listRooms();
        listItems.clear();
        for (Room r : rooms) listItems.add(r.toString());
        adapter.notifyDataSetChanged();
    }

    private void showAddRoomDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.item_room, null);
        final EditText etNumber = dialogView.findViewById(R.id.etRoomNumber);
        final EditText etType = dialogView.findViewById(R.id.etRoomType);
        final EditText etPrice = dialogView.findViewById(R.id.etRoomPrice);

        new AlertDialog.Builder(this)
                .setTitle("Add Room")
                .setView(dialogView)
                .setPositiveButton("Add", (d, which) -> {
                    String num = etNumber.getText().toString().trim();
                    String type = etType.getText().toString().trim();
                    String priceS = etPrice.getText().toString().trim();
                    if (num.isEmpty() || type.isEmpty() || priceS.isEmpty()) {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double price = Double.parseDouble(priceS);
                    manager.addRoom(num, type, price);
                    refreshRoomList();
                    Toast.makeText(this, "Room added", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showNewBookingDialog() {
        // simple inputs: guestName, preferred type
        final EditText etName = new EditText(this);
        etName.setHint("Guest name");
        etName.setInputType(InputType.TYPE_CLASS_TEXT);

        final EditText etType = new EditText(this);
        etType.setHint("Preferred type (Single/Double) - optional");
        etType.setInputType(InputType.TYPE_CLASS_TEXT);

        // stack them in layout
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.addView(etName);
        layout.addView(etType);

        new AlertDialog.Builder(this)
                .setTitle("New Booking")
                .setView(layout)
                .setPositiveButton("Book", (d, w) -> {
                    String guest = etName.getText().toString().trim();
                    String pref = etType.getText().toString().trim();
                    if (guest.isEmpty()) {
                        Toast.makeText(this, "Enter guest name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Booking b = manager.createBooking(guest, pref);
                    if (b.getAssignedRoom() != null) {
                        Toast.makeText(this, "Assigned room: " + b.getAssignedRoom(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Added to waiting list", Toast.LENGTH_LONG).show();
                    }
                    refreshRoomList();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showCheckoutDialog() {
        final EditText etRoom = new EditText(this);
        etRoom.setHint("Room number to checkout");
        etRoom.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Checkout Room")
                .setView(etRoom)
                .setPositiveButton("Checkout", (d, w) -> {
                    String roomNo = etRoom.getText().toString().trim();
                    if (roomNo.isEmpty()) {
                        Toast.makeText(this, "Enter room number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean ok = manager.checkoutRoom(roomNo);
                    if (ok) {
                        Toast.makeText(this, "Checked out " + roomNo, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed: room not occupied or doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                    refreshRoomList();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showWaitingListDialog() {
        List<Booking> wait = manager.getWaitingList();
        if (wait.isEmpty()) {
            Toast.makeText(this, "Waiting list is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Booking b : wait) {
            sb.append(b.toString()).append("\n");
        }
        new AlertDialog.Builder(this)
                .setTitle("Waiting List")
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    private void showActionsDialog() {
        List<String> acts = manager.getRecentActions();
        if (acts.isEmpty()) {
            Toast.makeText(this, "No recent actions", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : acts) sb.append(s).append("\n");
        new AlertDialog.Builder(this)
                .setTitle("Recent Actions (newest first)")
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}
