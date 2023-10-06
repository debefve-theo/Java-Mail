package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Exemple de fenêtre Swing avec chargement");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 200);

            JButton startButton = new JButton("Démarrer le chargement");
            mainFrame.add(startButton);

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Créez et affichez la boîte de dialogue de chargement
                    JDialog loadingDialog = createLoadingDialog(mainFrame);
                    loadingDialog.setVisible(true);

                    // Exécutez la tâche longue dans un thread séparé (SwingWorker)
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            // Exécutez votre tâche longue ici
                            Thread.sleep(5000); // Exemple : attendez 5 secondes
                            return null;
                        }

                        @Override
                        protected void done() {
                            // Fermez la boîte de dialogue de chargement une fois la tâche terminée
                            loadingDialog.dispose();
                        }
                    };

                    worker.execute();
                }
            });

            mainFrame.setVisible(true);
        });
    }

    private static JDialog createLoadingDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Chargement en cours", true);
        JPanel panel = new JPanel(new BorderLayout());
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Barre de progression indéterminée
        panel.add(progressBar, BorderLayout.CENTER);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        return dialog;
    }
}