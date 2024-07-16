import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingList {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField itemField;
    private JTextField quantityField;
    private JTextField valueField;

    private JTextField totalCompraField;




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingList().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Lista de Compras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        model = new DefaultTableModel(new Object[]{"Item", "Quantidade", "Valor"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        itemField = new JTextField(15);
        quantityField = new JTextField(5);
        valueField = new JTextField(7);
        totalCompraField = new JTextField(7);

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



        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valueField);

        inputPanel.add(new JLabel("Total da Compra"));
        inputPanel.add(totalCompraField);



        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        frame.getContentPane().add(scrollPane, "Center");
        frame.getContentPane().add(inputPanel, "South");

        frame.setVisible(true);
    }

    private void addItem() {
        String item = itemField.getText();
        String quantity = quantityField.getText();
        String value = valueField.getText();

        if (item.isEmpty() || quantity.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.addRow(new Object[]{item, quantity, value});

        itemField.setText("");
        quantityField.setText("");
        valueField.setText("");
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Nenhum item selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
