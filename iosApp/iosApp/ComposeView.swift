//
//  ComposeView.swift
//  iosApp
//
//  Created by Yuriy G on 27.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Foundation
import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
}
