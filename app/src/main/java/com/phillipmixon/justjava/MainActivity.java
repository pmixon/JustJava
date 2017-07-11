package com.phillipmixon.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText customerName = (EditText) findViewById(R.id.customer_name_edittext);

        boolean addWhippedCream = whippedCream.isChecked();
        boolean addChocolate = chocolate.isChecked();

        int price = calculatePrice(addWhippedCream, addChocolate);

        String customer = customerName.getText().toString();
        displayMessage(createOrderSummary(customer, price, addWhippedCream, addChocolate));
    }

    /**
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @param price           of the order
     * @return text summary
     */
    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\nAdd whipped cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: $" + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage = priceMessage + "\nThank you!";

        return priceMessage;
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
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int price = 5;
        if (addWhippedCream) {
            price = price + 1;
        }
        if (addChocolate) {
            price = price + 2;
        }

        return price * quantity;
    }

    public void updateQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
