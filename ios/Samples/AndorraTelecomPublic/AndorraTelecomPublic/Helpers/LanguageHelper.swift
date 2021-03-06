/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
import Foundation

class LanguageHelper {

    private var languageApp: String

    private static var sharedLanguageHelper: LanguageHelper = {
        let languageHelper = LanguageHelper()
        return languageHelper
    }()

    class func shared() -> LanguageHelper {
        return sharedLanguageHelper
    }

    init() {
        self.languageApp = NSLocale.current.languageCode!
    }

    func change(language: String) {
        let endIndex = language.index(language.startIndex, offsetBy: 2)
        self.languageApp = language.substring(to: endIndex)
    }

    func currentLanguage() -> String {
        return languageApp
    }

    let listLanguages = ["cat", "esp", "fra", "eng"]

    var formattedId: String {
        switch languageApp {
        case "ca":
            return "ca_ES"
        case "es":
            return "es_ES"
        case "fr":
            return "fr_FR"
        default:
            return "en_US"
        }
    }

    var threeLettersFormatted: String {
        switch languageApp {
        case "ca":
            return "cat"
        case "es":
            return "esp"
        case "fr":
            return "fra"
        default:
            return "eng"
        }
    }

    func url(page: Pages) -> String {
        let url = "https://www.andorratelecom.ad/c/portal/update_language?p_l_id=\(page.plid)&redirect=\(page.pathName)&languageId=\(self.formattedId)"
        print("\nURL: \(url)")

        return url
    }

    enum Pages: String {
        case index
        case mobile
        case roaming
        case paquete69
        case optima
        case legal
        case map

        var plid: String {
            switch self {
            case .index:
                return "34684"
            case .mobile:
                return "839763"
            case .roaming:
                return "841024"
            case .paquete69:
                return "108620"
            case .optima:
                return "106754"
            case .legal:
                return "34691"
            case .map:
                return "33998"
            }
        }

        var pathName: String {
            switch self {
            case .index:
                return "/particulars/inici/"
            case .mobile:
                return "/particulars/telefonia-mobil/forfets-mobiland/contracte/"
            case .roaming:
                return "/particulars/telefonia-mobil/serveis-complementaris/forfet-roaming/"
            case .paquete69:
                return "/particulars/internet-fixa/internet-i-telefonia-fixa/paquet-69/"
            case .optima:
                return "/particulars/internet-fixa/internet-i-telefonia-fixa/optima/"
            case .legal:
                return "/avis-legal/"
            case .map:
                return "/web/comercial/on-som"
            }
        }
    }

}

extension String {
    func localized() -> String? {
        if let path = Bundle.main.path(forResource: LanguageHelper.shared().currentLanguage(), ofType: "lproj") {
            if let bundle = Bundle(path: path) {
                return NSLocalizedString(self, tableName: "translations", bundle: bundle, value: "", comment: "")
            }
        }

        return nil
    }
}
