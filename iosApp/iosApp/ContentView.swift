import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ZStack {
            Color(hex: McsTheme().getBackgroundColorHex()).edgesIgnoringSafeArea(.all)

            ComposeView()
                .ignoresSafeArea(.keyboard)
        }
    }
}

extension Color {
    init(hex: String) {
        let hex = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var int: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&int)
        let red, green, blue, alpha: Double
        if hex.count == 8 {
            alpha = Double((int >> 24) & 0xff) / 255.0
            red = Double((int >> 16) & 0xff) / 255.0
            green = Double((int >> 8) & 0xff) / 255.0
            blue = Double(int & 0xff) / 255.0
        } else {
            alpha = 1.0
            red = Double((int >> 16) & 0xff) / 255.0
            green = Double((int >> 8) & 0xff) / 255.0
            blue = Double(int & 0xff) / 255.0
        }
        self.init(.sRGB, red: red, green: green, blue: blue, opacity: alpha)
    }
}
