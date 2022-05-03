/*
 *  Copyright (c) 2020 Arthur Milchior <arthur@milchior.fr>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.libanki.template

import com.ichi2.utils.KotlinCleanup

object MathJax {
    // MathJax opening delimiters
    private val sMathJaxOpenings = arrayOf("\\(", "\\[")

    // MathJax closing delimiters
    private val sMathJaxClosings = arrayOf("\\)", "\\]")

    @KotlinCleanup("fix IDE lint issues")
    fun textContainsMathjax(txt: String): Boolean {
        // Do you have the first opening and then the first closing,
        // or the second opening and the second closing...?

        // This assumes that the openings and closings are the same length.
        var opening: String
        var closing: String
        for (i in sMathJaxOpenings.indices) {
            opening = sMathJaxOpenings[i]
            closing = sMathJaxClosings[i]

            // What if there are more than one thing?
            // Let's look for the first opening, and the last closing, and if they're in the right order,
            // we are good.
            val first_opening_index = txt.indexOf(opening)
            val last_closing_index = txt.lastIndexOf(closing)
            if (first_opening_index != -1 && last_closing_index != -1 && first_opening_index < last_closing_index) {
                return true
            }
        }
        return false
    }
}
