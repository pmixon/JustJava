 package com.phillipmixon.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View View) {
        int price = calculatePrice();
        //displayMessage("Total: " + Integer.toString(price));
        String totalMessage = "Total: $" + price;
        totalMessage = totalMessage + "\n Thank you!";
        displayMessage(totalMessage);
    }

    public void increaseQuantity(View View) {
        quantity++;
        updateQuantity(quantity);
    }

    public void decreaseQuantity(View View) {
        quantity--;
        updateQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    public int calculatePrice() {
        return (quantity * 5);
    }

    public void updateQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.total_text);
        priceTextView.setText(message);
    }

}
