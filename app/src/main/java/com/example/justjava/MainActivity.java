/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "you can not have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "you cannot have less than 1  coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText)  findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream " +  hasWhippedCream);

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = creatOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);


    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // price of one cup of coffee
        int basePrice = 5 ;

        //add $1 doller if the user weants whipped cream
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        //add $1 doller if the user weants chocolate
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice ;
    }

    private String creatOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
       orderSummaryTextView.setText(message);
    }
}