import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingList2 {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField itemField;
    private JTextField quantityField;
    private JTextField valueField;
    private JTextField weightField;
    private JLabel totalLabel;




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingList2().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Gastos Mensais");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);


        model = new DefaultTableModel(new Object[]{"Nº", "Item", "Quantidade", "Valor Unitário","Valor", "Kg"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);


        itemField = new JTextField(15);
        quantityField = new JTextField(5);
        valueField = new JTextField(7);
        weightField = new JTextField(5);





        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });


        totalLabel = new JLabel("Valor Total: 0.00");

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);

        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valueField);

        inputPanel.add(new JLabel("Peso:"));
        inputPanel.add(weightField);

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        JPanel totalPanel = new JPanel();
        totalPanel.add(totalLabel);

        frame.getContentPane().add(scrollPane, "Center");
        frame.getContentPane().add(inputPanel, "South");
        frame.getContentPane().add(totalPanel, "North");

        frame.setVisible(true);
    }

    private void addItem() {
        String item = itemField.getText();
        String quantityStr = quantityField.getText();
        String valueStr = valueField.getText();
        String weightStr = weightField.getText();

        if (item.isEmpty() || quantityStr.isEmpty() || valueStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            double value = Double.parseDouble(valueStr);
            int rowCount = model.getRowCount() + 1;
            model.addRow(new Object[]{rowCount, item, quantity, value ,value * quantity});
            updateTotal();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Quantidade e Valor devem ser números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        itemField.setText("");
        quantityField.setText("");
        valueField.setText("");
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            updateItemNumbers();
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(frame, "Nenhum item selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            total += (double) model.getValueAt(i, 4);
        }
        totalLabel.setText("Valor Total: " + String.format("%.2f", total));
    }

    private void updateItemNumbers() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }
    }
}
