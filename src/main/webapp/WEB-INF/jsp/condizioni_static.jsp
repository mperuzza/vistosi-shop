<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<security:authentication property="principal.cliente.archclie.cdlist" var="cdlist"/>
<div>
    <c:choose>
        <c:when test="${cdlist == 'L01' || cdlist == '1'}">
            <c:choose><c:when test="${rc.locale.language =='it'}">
                    <h2><spring:message code="condc.label.condl" text="Condizioni fisse da listino"/></h2>
                    <dl>
                        <dt>Portale per i servizi alla clientela: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Validit&agrave; del presente listino dal 01.01.2021 al 31.12.2021
                            <br><br>
                            1. Condizioni di vendita personalizzate: le seguenti condizioni di vendita sono generali. Le condizioni di vendita dettagliate e personalizzate sono pubblicate nel portale MyVistosi (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). Le condizioni di vendita avranno validità annuale con scadenza il 31/12, senza tacito rinnovo. I Clienti che non acquisteranno alcun prodotto per un periodo superiore a 12 mesi saranno considerati inattivi e tutte le condizioni di vendita personalizzate non saranno più in vigore.
                            <br>
                            2. Prodotti fuori standard: in caso di ordini di prodotti fuori listino, di prodotti personalizzati o di prodotti con modifiche dallo standard, Vetreria Vistosi Srl si riserva il diritto di formulare delle offerte con condizioni di vendita diverse da quelle accordate.
                            <br>
                            3. Tempi di consegna e disponibilità di magazzino: per la merce non a magazzino, i tempi di consegna medi sono di 3/4 settimane. Le giacenze vengono aggiornate quotidianamente e sono disponibili nel portale MyVistosi.
                            <br>
                            4. Controllo della merce alla ricezione: il Cliente è tenuto a controllare accuratamente la merce prima di consegnarla o di spedirla all’utente finale. Non saranno accettati reclami per merce non presente nel luogo di destinazione indicato nei documenti di trasporto.
                            <br>
                            5. Danni evidenti da trasporto: eventuali danni da trasporto alla merce o all’imballo della merce stessa, evidenti al momento della consegna, come pure differenze di quantità e/o di modello rispetto a quanto indicato nel documento di trasporto, devono essere segnalate con riserva specifica e dettagliata nella lettera di vettura del corriere.
                            <br>
                            6. Danni occulti da trasporto: eventuali danni da trasporto alla merce, non evidenti al momento della consegna o non ragionevolmente presumibili al momento della consegna, devono essere segnalati entro 8 giorni dal ricevimento, nella sezione reclami del portale MyVistosi.
                            <br>
                            7. Caratteristiche del vetro di Murano: le lavorazioni completamente manuali, i complessi processi di fusione, la raffinatezza delle miscele chimiche dei vetri di Vistosi, tipiche delle antiche tecniche di lavorazione del vetro di Murano, possono dar luogo a leggere imprecisioni dei colori, nelle forme e nelle dimensioni, negli spessori e nei decori. Più che imperfezioni o difformità di qualità sono da considerarsi espressione del vetro lavorato a mano e soffiato a bocca. Piccole bolle irregolari, venature, piccoli puntini di materiale denso e altre imperfezioni devono quindi essere tollerate, in particolar modo nelle forme e nelle lavorazioni più complesse o nei modelli di medio-grandi dimensioni.
                            <br>
                            8. Riserva di modica di prodotti: Vetreria Vistosi Srl si riserva di modificare in qualsiasi momento e senza preavviso i propri prodotti. Il Cliente che dovesse avere delle necessità specifiche o che dovesse abbinare i modelli ordinati ad altri già in suo possesso è tenuto a specificarlo nell’ordine.
                            <br>
                            9. Modelli fuori produzione: Vetreria Vistosi Srl si riserva il diritto di eliminare dalla produzione uno o più modelli senza darne preavviso. Il Cliente si impegna a segnalare tempestivamente qualsiasi progetto o quotazione, per dare modo all’Azienda di tenerne conto nella scelta dei modelli da eliminare dalla produzione.
                            <br>
                            10. Gestione ordini: gli ordini saranno considerati accettati solo al momento dell’invio della conferma d’ordine via e-mail all’indirizzo indicato dal Cliente e riportato nella medesima scheda Cliente presente nel portale MyVistosi. Il Cliente si impegna a richiedere modifiche alle conferme d’ordine per qualsiasi ragione entro e non oltre 2 giorni lavorativi dal ricevimento della conferma d’ordine. Trascorso questo termine gli ordini non saranno modificabili o cancellabili.
                            <br>
                            11. Aggiunte ordine: le aggiunte d’ordine che, per mancanza di disponibilità di magazzino, restassero inevase, verranno considerate come ordini a sé stanti.
                            <br>
                            12. Spedizioni parziali: spedizioni parziali di ordini su sollecito del cliente, verranno considerate come ordini a sé stanti.
                            <br>
                            13. Garanzia: tutti i modelli sono garantiti per un periodo di 2 anni dalla data del documento di vendita di Vetreria Vistosi Srl.
                            <br>
                            14. Esclusioni: i prezzi indicati in questo listino non sono comprensivi di IVA, imballi speciali, pallet, lampadine e tutto quanto non espressamente indicato.
                            <br>
                            15. Resa della merce: Franco Fabbrica. Salvo diversi accordi pubblicati nell’area riservata del sito aziendale <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>. 16. Foro competente: per ogni eventuale controversia di qualsiasi genere e sorta in qualsiasi luogo sarà competente il foro di Venezia e la legge applicabile sarà quella Italiana.
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${rc.locale.language =='de'}">
                    <h2><spring:message code="condc.label.condl" text="Verkaufsbedingungen"/></h2>
                    <dl>
                        <dt>Portal für den Kundenservice: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Gültigkeit dieser Preisliste vom 01.01.2021 bis 31.12.2021
                            <br><br>
                            1. Kundenspezifische Verkaufsbedingungen: Die folgenden Verkaufsbedingungen sind allgemein. Die detaillierten und individuellen Verkaufsbedingungen werden auf dem MyVistosi-Portal (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>) veröffentlicht. Die Verkaufsbedingungen gelten für ein Jahr bis zum 31.12. Ohne stillschweigende Verlängerung. Kunden, die über einen Zeitraum von mehr als 12 Monaten kein Produkt kaufen, gelten als inaktiv und alle personalisierten Verkaufsbedingungen gelten nicht mehr.
                            <br> 2. Nicht standardisierte Produkte: Bei Bestellungen von nicht gelisteten Produkten, kundenspezifischen Produkten oder Produkten, die vom Standard abweichen, behält sich Vetreria Vistosi Srl das Recht vor, Angebote mit anderen als den vereinbarten Verkaufsbedingungen abzugeben.
                            <br> 3. Lieferzeiten und Lagerverfügbarkeit: Für Waren, die nicht auf Lager sind, beträgt die durchschnittliche Lieferzeit 3/4 Wochen. Die Bestände werden täglich aktualisiert und sind auf dem MyVistosi-Portal verfügbar.
                            <br> 4. Kontrolle der Ware nach deren Erhalt: Der Kunde ist verpflichtet, die Ware sorgfältig zu prüfen, bevor er sie an den Endverbraucher abgibt oder sendet. Für Waren, die sich nicht an dem in den Transportdokumenten angegebenen Bestimmungsort befinden, werden keine Reklamationen anerkannt.
                            <br> 5. Offensichtliche Transportschäden: Transportschäden an der Ware oder an der Verpackung der Ware selbst, die zum Zeitpunkt der Lieferung erkennbar sind, sowie Unterschiede in Menge und / oder Modellen im Vergleich zu den Angaben im Transportdokument sind mit spezifischem und detailliertem Vorbehalt im Kurier-Frachtbrief zu melden.
                            <br> 6. Versteckte Schäden während des Transportes: Transportschäden an der Ware, die zum Zeitpunkt der Lieferung nicht erkennbar oder zum Zeitpunkt der Lieferung nicht vermutbar sind, müssen innerhalb von 8 Tagen nach Erhalt in der Sektion “Beschwerden” des MyVistosi-Portals gemeldet werden.
                            <br> 7. Eigenschaften des Murano-Glases: Die vollkommen manuelle Verarbeitung, die komplexen Schmelzprozesse und die Veinheiten der chemischen Gemische des VistosiGlases, welche für die antiken Techniken der Murano-Glasverarbeitung typisch sind, können leichte Ungenauigkeiten in den Farben, Formen und Größen, Stärken und Dekorationen aufweisen. Sie sind mehr als Unvollkommenheiten oder Qualitätsunterschiede, sie sind Ausdruck von handgefertigtem und mundgeblasenem Glas. Kleine unregelmäßige Blasen, Venen, kleine Punkte aus dichtem Material und andere Unvollkommenheiten müssen daher toleriert werden, insbesondere bei komplexeren Formen und Prozessen oder bei mittelgroßen Modellen.
                            <br> 8. Produktänderungsreserve: Vetreria Vistosi Srl behält sich das Recht vor, seine Produkte jederzeit und ohne vorherige Ankündigung zu ändern. Der Kunde, der spezielle Bedürfnisse hat oder die bestellten Modelle mit anderen in seinem Besitz befindlichen kombinieren will, muss dies in der Bestellung angeben.
                            <br> 9. Auslaufmodelle: Vetreria Vistosi Srl behält sich das Recht vor, ein oder mehrere Modelle ohne vorherige Ankündigung aus der Produktion zu nehmen. Der Kunde verpflichtet sich, jedes Projekt oder Angebot unverzüglich zu melden, damit das Unternehmen diese bei der Auswahl der Modelle, die aus der Produktion ausgeschlossen werden, berücksichtigen kann.
                            <br> 10. Auftragsverwaltung: Bestellungen gelten erst dann als angenommen, wenn die Auftragsbestätigung per E-Mail an die vom Kunden angegebene und im Kundenbereich im Portal MyVistosi vermerkte Adresse übermittelt wurde. Der Kunde verpflichtet sich, etwaige Änderungen der Auftragsbestätigungen aus jedwedem Grund, bis spätestens 2 Werktage nach Erhalt derselben zu beantragen. Nach Ablauf dieser Frist können die Bestellungen nicht mehr storniert oder geändert werden.
                            <br> 11. Auftragserweiterungen: Auftragserweiterungen, die aufgrund mangelnder Lagerverfügbarkeit nicht erfüllt werden, gelten als separate Bestellungen.
                            <br> 12. Teillieferungen: die vom Kunden gewünschten Teillieferungen von Bestellungen gelten als separate Bestellungen.
                            <br> 13. Garantie: Für alle Modelle gilt, ab dem Datum des Verkaufsbelegs von Vetreria Vistosi Srl, eine Garantie von 2 Jahren
                            <br> 14. Ausschlüsse: Die in dieser Preisliste angegebenen Preise enthalten keine Mehrwertsteuer, Sonderverpackungen, Paletten, Glühbirnen und anderes, wenn nicht ausdrücklich angegeben.
                            <br> 15. Warenlieferung: Ab Werk. Sofern nicht anders vereinbart und im reservierten Bereich des Firmenportals “MyVistosi” <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a> veröffentlicht.
                            <br> 16. Gerichtsstand: Für alle Streitigkeiten, egal welcher Art sie sind und an welchem Ort sie auftreten, ist das Gericht Venedig zuständig. Es gilt italienisches Recht
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${rc.locale.language =='es'}">
                    <h2><spring:message code="condc.label.condl" text="Condiciones de venta"/></h2>
                    <dl>
                        <dt>Portal de atención al cliente: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            Validez de esta lista de precios desde el 01.01.2021 hasta el 31.12.2021
                            <br><br>
                            1. Condiciones de venta personalizadas: las siguientes condiciones de venta son generales. Las condiciones de venta detalladas y personalizadas se publican en el portal MyVistosi (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). Las condiciones de venta tendrán validez anual hasta el 31/12, sin renovación tácita. Los clientes que no adquieran ningún producto por un período superior a 12 meses serán considerados inactivos y todas las condiciones de venta personalizadas perderán validez.
                            <br> 2. Productos no estándar: en el caso de pedidos de productos no incluidos en el catálogo-tarifa, productos personalizados o productos con cambios respecto al estándar, Vetreria Vistosi Srl se reserva el derecho de realizar ofertas con condiciones de venta diferentes a las acordadas.
                            <br> 3. Plazos de entrega y disponibilidad de stock: para las mercancías fuera de stock, el plazo medio de entrega es de 3/4 semanas. Las existencias se actualizan diariamente y están disponibles en el portal MyVistosi.
                            <br> 4. Inspección de la mercancía al recibirla: el Cliente debe verificar cuidadosamente la mercancía antes de entregarla o enviarla al usuario final. No se aceptarán reclamaciones por mercancías que no estén presentes en el lugar de destino indicado en los documentos de transporte.
                            <br> 5. Daños de transporte evidentes: cualquier daño de transporte de la mercancía o del embalaje de la misma, evidente en el momento de la entrega, así como las diferencias en cantidad y / o modelo con respecto a lo indicado en el documento de transporte, deberán ser comunicados con reserva específica y detallada en la carta de porte del transportista.
                            <br> 6. Daños ocultos durante el transporte: cualquier daño de transporte de la mercancía, no evidente en el momento de la entrega o no razonablemente presumible en el momento de la entrega, debe ser comunicado dentro de los 8 días de la entrega, en la sección de quejas del portal MyVistosi.
                            <br> 7. Características del vidrio de Murano: la elaboración completamente manual, los complejos procesos de fusión, el refinamiento de las mezclas químicas del vidrio de Vistosi, típico de las antiguas técnicas de elaboración del vidrio de Murano, pueden dar lugar a ligeras imprecisiones en los colores, en el formas y tamaños, espesores y decoraciones. Más que imperfecciones o diferencias de calidad, deben considerarse como la expresión de vidrio hecho a mano y soplado a boca. Por lo tanto, deben tolerarse pequeñas burbujas irregulares, venas, pequeños puntos de material denso y otras imperfecciones, especialmente en las formas y procesos más complejos o en modelos medio-grandes.
                            <br> 8. Reserva de modificación de productos: Vetreria Vistosi Srl se reserva el derecho de modificar sus productos en cualquier momento y sin previo aviso. El cliente que tenga necesidades específicas o que tenga que combinar los modelos pedidos con otros que ya en su poder, debe especificarlo en el pedido.
                            <br> 9. Modelos descatalogados: Vetreria Vistosi Srl se reserva el derecho de retirar uno o más modelos de la producción sin previo aviso. El Cliente se compromete a comunicar con prontidud cualquier proyecto o presupuesto a procesar, para que Vistosi los tenga en cuenta a la hora de elegir los modelos a eliminar de la producción.
                            <br> 10. Gestión de pedidos: los pedidos se considerarán aceptados únicamente cuando la confirmación del pedido se envíe por correo electrónico a la dirección indicada por el Cliente e indicada en la página del Cliente en el portal MyVistosi. El Cliente se compromete a solicitar cambios en las confirmaciones del pedido por cualquier motivo en un plazo máximo de 2 días hábiles desde la recepción de la confirmación del pedido. Pasado este plazo los pedidos no serán modificables ni cancelados.
                            <br> 11. Adiciones a los pedidos: las adiciones a los pedidos que, por falta de disponibilidad en el almacén, no se hayan cumplido, se considerarán como pedidos separados del anterior.
                            <br> 12. Envíos parciales: los envíos parciales de pedidos a petición del cliente se considerarán como pedidos separados.
                            <br> 13. Garantía: todos los modelos están garantizados por un período de 2 años a partir de la fecha del documento de venta de Vetreria Vistosi Srl.
                            <br> 14. Exclusiones: los precios indicados en esta lista de precios no incluyen IVA, embalajes especiales, paletas, bombillas y todo cuando no se indique expresamente.
                            <br> 15. Devolución de mercancías: Ex Works. Salvo acuerdo en contrario, publicado en el área reservada del sitio web de la empresa <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>.
                            <br> 16. Jurisdicción: para cualquier disputa de cualquier tipo y que surja en cualquier lugar será competente el tribunal de Venecia y la ley aplicable será la italiana.
                        </dd>
                    </dl>


                </c:when>
                <c:when test="${rc.locale.language =='fr'}">
                    <h2><spring:message code="condc.label.condl" text="Conditions de vente"/></h2>
                    <dl>
                        <dt>Portail web pour le service clients: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            Validité de cette liste de prix du 01.01.2021 au 31.12.2021
                            <br><br>
                            1. Conditions de vente personnalisées: les conditions de vente suivantes sont générales. Les conditions de vente détaillées et personnalisées sont publiées sur le portail MyVistosi (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). Les conditions de vente seront valables pour la période d’un an avec expiration le 31/12, sans renouvellement tacite. Les clients qui n’achètent aucun produit pendant une période supérieure à 12 mois seront considérés comme inactifs et toutes les conditions de vente personnalisées ne seront plus en vigueur.
                            <br> 2. Produits non standard: en cas de commande de produits sans prix, de produits personnalisés ou de produits avec des modifications par rapport à la norme, Vetreria Vistosi Srl se réserve le droit de faire des offres avec des conditions de vente différentes de celles convenues.
                            <br> 3. Délais de livraison et disponibilité des stocks: pour les produits qui ne sont pas en stock, les délais moyens de livraison sont de 3/4 semaines. Les stocks sont mis à jour quotidiennement et sont disponibles sur le portail MyVistosi.
                            <br> 4. Contrôle des marchandises à la réception: le client est tenu de vérifier soigneusement les marchandises avant de les livrer ou de les envoyer à l’utilisateur final. Les réclamations ne seront pas acceptées pour les marchandises qui ne se trouvent pas au lieu de destination indiqué dans les documents de transport.
                            <br> 5. Dommages de transport manifestes: tout dommage de transport aux marchandises ou à l’emballage des marchandises elles-mêmes, évident au moment de la livraison, ainsi que les différences de quantité et / ou de modèle par rapport à ce qui est indiqué dans le document de transport, doivent être signalés sous réserve spécifique et détaillée dans la lettre de transport.
                            <br> 6. Dommages cachés pendant le transport: tout dommage de transport à la marchandise, qui ne soit pas évident au moment de la livraison ou qui ne soit pas raisonnablement prévisible au moment de la livraison, doit être signalé dans les 8 jours suivants la réception, dans la section des réclamations du portail MyVistosi.
                            <br> 7. Caractéristiques du verre de Murano: le traitement entièrement manuel, les processus de fusion complexes, le raffinement des mélanges chimiques du verre Vistosi, typiques des anciennes techniques de traitement du verre de Murano, peuvent donner lieu à de légères (inexactitudes) variations dans les couleurs, dans les formes et dimensions, épaisseurs et décorations. Plutôt que des imperfections ou des différences de qualité, ils doivent être considérés comme une expression du verre travaillé à la main et soufflé à la bouche. Les petites bulles irrégulières, les veines, les petits points de matière dense et autres imperfections doivent donc être tolérés, en particulier dans les formes et procédés les plus complexes ou dans les modèles de taille moyenne et grande.
                            <br> 8. Réserve de modification des produits: Vetreria Vistosi Srl se réserve le droit de modifier ses produits à tout moment et sans préavis. Le Client qui a des besoins spécifiques ou qui doit combiner les modèles commandés avec d’autres déjà en sa possession est tenu de le préciser dans la commande.
                            <br> 9. Modèles abandonnés: Vetreria Vistosi Srl se réserve le droit de retirer un ou plusieurs modèles de la production sans préavis. Le Client s’engage à signaler dans les meilleurs délais tout projet ou devis, pour permettre à la Société de les tenir en compte lors du choix des modèles à éliminer de la production.
                            <br> 10. Gestion des commandes: les commandes ne seront considérées comme acceptées que lorsque la confirmation de commande est envoyée par e-mail à l’adresse indiquée par le Client et mentionnée dans le même onglet Client sur le portail MyVistosi. Le Client s’engage à demander des modifications des confirmations de commande, quelque soit la raison, au plus tard dans les 2 jours ouvrables à compter de la date de réception de la confirmation de commande. Passé ce délai, les commandes ne seront ni modifiables ni elles pourront être annulées.
                            <br> 11. Ajout de commandes: les ajouts de commandes qui, en raison d’un manque de stock disponible, ne sont pas exécutés, seront considérés comme des commandes distinctes.
                            <br> 12. Envois partiels: les envois partiels de commandes sur sollicitation du client seront considérés comme des commandes séparées.
                            <br> 13. Garantie: tous les modèles sont garantis pour une période de 2 ans à compter de la date du document de vente Vetreria Vistosi Srl.
                            <br> 14. Exclusions: les prix indiqués dans cette liste de prix ne comprennent pas la TVA, les emballages spéciaux, les palettes, les ampoules et l’ensemble lorsqu’ils ne sont pas expressément indiqués.
                            <br> 15. livraisons: départ usine. Sauf accord contraire, publié dans la zone réservée du site Web « MyVistosi » de la société <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>.
                            <br> 16. Juridiction: pour tout litige de quelque nature qu’il s’agisse et survenant en tout lieu, le tribunal de Venise sera compétent et la loi applicable sera la loi italienne.
                        </dd>
                    </dl>

                </c:when>
                <c:otherwise>
                    <h2><spring:message code="condc.label.condl" text="Sales conditions"/></h2>
                    <dl>
                        <dt>Customer service web site: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Validity of this price list from 01.01.2021 to 31.12.2021
                            <br><br>
                            1. Personalized conditions of sales: the following are General conditions of sale.The detailed and personalized conditions of sale are published in the MyVistosi portal (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). The conditions of sale will be valid for one year expiring on 31/12, without tacit renewal. Customers who do not purchase any products for a period exceeding 12 months will be considered inactive and all personalized sales conditions will no longer be in force.
                            <br> 2. Non-standard products: in the case of orders for products that are not listed in the price list, customized products or products with modifications from the standard, Vetreria Vistosi Srl reserves the right to make offers with conditions of sale different from those agreed.
                            <br> 3. Delivery times and stock availability: for goods not in stock, the average delivery times are 3/4 weeks. Stocks are updated daily and are available on the MyVistosi portal.
                            <br> 4. Inspection of goods upon receipt: the Customer is required to carefully check the goods before delivering them or sending them to the end user. Complaints will not be accepted for goods not available at the place of destination indicated in the transport documents.
                            <br> 5. Any damage to goods caused during transport: any transport damage to goods or to the packaging of goods, evident at the time of delivery, as well as differences in quantity and / or model compared to what is indicated in the transport document, must be reported with specific and detailed reservation in the courier waybill.
                            <br> 6. Hidden damage during transport: any transport damage to goods, not evident at the time of delivery or not reasonably foreseeable at the time of delivery, must be reported within 8 days from the delivery, in the complaints section of the MyVistosi portal.
                            <br> 7. Characteristics of Murano glass: the completely manual processing, the complex melting processes, the refinement of the chemical mixtures of Vistosi glass, typical of the ancient processings techniques of Murano glass, can give rise to slight inaccuracies in the colors, in the shapes and sizes, thicknesses and decorations. Rather than imperfections or quality differences, they must be considered an expression of hand-worked and mouth-blown glass processing. Small irregular bubbles, veins, small dots of dense material and other imperfections must therefore be tolerated, especially in the more complex shapes and processes or in medium-large models.
                            <br> 8. Product modification reserve: Vetreria Vistosi Srl reserves the right to modify its products at any time and without notice. The customer who has specific needs or who should combine the models ordered with others already in his possession must specify this in the order.
                            <br> 9. Discontinued models: Vetreria Vistosi Srl reserves the right to remove one or more models from production without giving prior notice. The Customer agree to promptly report any project or quotation in order to allow the Company to take them into account in the choice of models to be removed from production and discontinued.
                            <br> 10. Order management: orders will be considered accepted only when the order confirmation is sent by e-mail to the address indicated by the Customer and reported in the Customer data shit in the MyVistosi portal. The Customer undertakes to request changes to the order confirmations for any reason no later than 2 working days from receipt of the order confirmation. After this deadline, orders will not be modifiable or cancellable.
                            <br> 11. Additions to orders: additions to orders that, due to lack of stock availability, remain unfulfilled, will be considered as separate orders.
                            <br> 12. Partial shipments: partial shipments of orders upon customer reminder will be considered as separate orders.
                            <br> 13. Warranty: all models are guaranteed for a period of 2 years from the date of the Vetreria Vistosi Srl sales document.
                            <br> 14. Exclusions: the prices indicated in this price list do not include VAT, special packaging, pallets, light bulbs and all when not expressly indicated.
                            <br> 15. Return of goods: Ex Works. Unless otherwise agreed, published in the reserved area of the company website <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>.
                            <br> 16. Jurisdiction: for any dispute of any kind and arising in any place the court of Venice will be competent and the applicable law will be the Italian one.
                        </dd>
                    </dl>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${cdlist == 'LUS' || cdlist == '7'}">
            <h2><spring:message code="condc.label.condl" text="Sales conditions"/></h2>
            <dl>
                <dt>Customer service web site: MyVistosi - www.vistosi.it/portal</dt>
                <dd>Validity of this price list from 01.01.2021 to 31.12.2021
                    <br><br>
                    1. Personalized conditions of sales: the following are General conditions of sale.The detailed and personalized conditions of sale are published in the MyVistosi portal (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). The conditions of sale will be valid for one year expiring on 31/12, without tacit renewal. Customers who do not purchase any products for a period exceeding 12 months will be considered inactive and all personalized sales conditions will no longer be in force.
                    <br> 2. Non-standard products: in the case of orders for products that are not listed in the price list, customized products or products with modifications from the standard, Vetreria Vistosi Srl reserves the right to make offers with conditions of sale different from those agreed.
                    <br> 3. Delivery times and stock availability: for goods not in stock, the average delivery times are 3/4 weeks. Stocks are updated daily and are available on the MyVistosi portal.
                    <br> 4. Inspection of goods upon receipt: the Customer is required to carefully check the goods before delivering them or sending them to the end user. Complaints will not be accepted for goods not available at the place of destination indicated in the transport documents.
                    <br> 5. Any damage to goods caused during transport: any transport damage to goods or to the packaging of goods, evident at the time of delivery, as well as differences in quantity and / or model compared to what is indicated in the transport document, must be reported with specific and detailed reservation in the courier waybill.
                    <br> 6. Hidden damage during transport: any transport damage to goods, not evident at the time of delivery or not reasonably foreseeable at the time of delivery, must be reported within 8 days from the delivery, in the complaints section of the MyVistosi portal.
                    <br> 7. Characteristics of Murano glass: the completely manual processing, the complex melting processes, the refinement of the chemical mixtures of Vistosi glass, typical of the ancient processings techniques of Murano glass, can give rise to slight inaccuracies in the colors, in the shapes and sizes, thicknesses and decorations. Rather than imperfections or quality differences, they must be considered an expression of hand-worked and mouth-blown glass processing. Small irregular bubbles, veins, small dots of dense material and other imperfections must therefore be tolerated, especially in the more complex shapes and processes or in medium-large models.
                    <br> 8. Product modification reserve: Vetreria Vistosi Srl reserves the right to modify its products at any time and without notice. The customer who has specific needs or who should combine the models ordered with others already in his possession must specify this in the order.
                    <br> 9. Discontinued models: Vetreria Vistosi Srl reserves the right to remove one or more models from production without giving prior notice. The Customer agree to promptly report any project or quotation in order to allow the Company to take them into account in the choice of models to be removed from production and discontinued.
                    <br> 10. Order management: orders will be considered accepted only when the order confirmation is sent by e-mail to the address indicated by the Customer and reported in the Customer data shit in the MyVistosi portal. The Customer undertakes to request changes to the order confirmations for any reason no later than 2 working days from receipt of the order confirmation. After this deadline, orders will not be modifiable or cancellable.
                    <br> 11. Additions to orders: additions to orders that, due to lack of stock availability, remain unfulfilled, will be considered as separate orders.
                    <br> 12. Partial shipments: partial shipments of orders upon customer reminder will be considered as separate orders.
                    <br> 13. Warranty: all models are guaranteed for a period of 2 years from the date of the Vetreria Vistosi Srl sales document.
                    <br> 14. Exclusions: the prices indicated in this price list do not include VAT, special packaging, pallets, light bulbs and all when not expressly indicated.
                    <br> 15. Return of goods: Ex Works. Unless otherwise agreed, published in the reserved area of the company website <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>.
                    <br> 16. Jurisdiction: for any dispute of any kind and arising in any place the court of Venice will be competent and the applicable law will be the Italian one.
                </dd>
            </dl>
        </c:when>
        <c:when test="${cdlist == 'LCA' || cdlist == '6'}">
            <c:choose>
                <c:when test="${rc.locale.language =='fr'}">
                    <h2><spring:message code="condc.label.condl" text="Conditions de vente"/></h2>
                    <dl>
                        <dt>Portail web pour le service clients: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            Validité de cette liste de prix du 01.01.2021 au 31.12.2021
                            <br><br>
                            1. Conditions de vente personnalisées: les conditions de vente suivantes sont générales. Les conditions de vente détaillées et personnalisées sont publiées sur le portail MyVistosi (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). Les conditions de vente seront valables pour la période d’un an avec expiration le 31/12, sans renouvellement tacite. Les clients qui n’achètent aucun produit pendant une période supérieure à 12 mois seront considérés comme inactifs et toutes les conditions de vente personnalisées ne seront plus en vigueur.
                            <br> 2. Produits non standard: en cas de commande de produits sans prix, de produits personnalisés ou de produits avec des modifications par rapport à la norme, Vetreria Vistosi Srl se réserve le droit de faire des offres avec des conditions de vente différentes de celles convenues.
                            <br> 3. Délais de livraison et disponibilité des stocks: pour les produits qui ne sont pas en stock, les délais moyens de livraison sont de 3/4 semaines. Les stocks sont mis à jour quotidiennement et sont disponibles sur le portail MyVistosi.
                            <br> 4. Contrôle des marchandises à la réception: le client est tenu de vérifier soigneusement les marchandises avant de les livrer ou de les envoyer à l’utilisateur final. Les réclamations ne seront pas acceptées pour les marchandises qui ne se trouvent pas au lieu de destination indiqué dans les documents de transport.
                            <br> 5. Dommages de transport manifestes: tout dommage de transport aux marchandises ou à l’emballage des marchandises elles-mêmes, évident au moment de la livraison, ainsi que les différences de quantité et / ou de modèle par rapport à ce qui est indiqué dans le document de transport, doivent être signalés sous réserve spécifique et détaillée dans la lettre de transport.
                            <br> 6. Dommages cachés pendant le transport: tout dommage de transport à la marchandise, qui ne soit pas évident au moment de la livraison ou qui ne soit pas raisonnablement prévisible au moment de la livraison, doit être signalé dans les 8 jours suivants la réception, dans la section des réclamations du portail MyVistosi.
                            <br> 7. Caractéristiques du verre de Murano: le traitement entièrement manuel, les processus de fusion complexes, le raffinement des mélanges chimiques du verre Vistosi, typiques des anciennes techniques de traitement du verre de Murano, peuvent donner lieu à de légères (inexactitudes) variations dans les couleurs, dans les formes et dimensions, épaisseurs et décorations. Plutôt que des imperfections ou des différences de qualité, ils doivent être considérés comme une expression du verre travaillé à la main et soufflé à la bouche. Les petites bulles irrégulières, les veines, les petits points de matière dense et autres imperfections doivent donc être tolérés, en particulier dans les formes et procédés les plus complexes ou dans les modèles de taille moyenne et grande.
                            <br> 8. Réserve de modification des produits: Vetreria Vistosi Srl se réserve le droit de modifier ses produits à tout moment et sans préavis. Le Client qui a des besoins spécifiques ou qui doit combiner les modèles commandés avec d’autres déjà en sa possession est tenu de le préciser dans la commande.
                            <br> 9. Modèles abandonnés: Vetreria Vistosi Srl se réserve le droit de retirer un ou plusieurs modèles de la production sans préavis. Le Client s’engage à signaler dans les meilleurs délais tout projet ou devis, pour permettre à la Société de les tenir en compte lors du choix des modèles à éliminer de la production.
                            <br> 10. Gestion des commandes: les commandes ne seront considérées comme acceptées que lorsque la confirmation de commande est envoyée par e-mail à l’adresse indiquée par le Client et mentionnée dans le même onglet Client sur le portail MyVistosi. Le Client s’engage à demander des modifications des confirmations de commande, quelque soit la raison, au plus tard dans les 2 jours ouvrables à compter de la date de réception de la confirmation de commande. Passé ce délai, les commandes ne seront ni modifiables ni elles pourront être annulées.
                            <br> 11. Ajout de commandes: les ajouts de commandes qui, en raison d’un manque de stock disponible, ne sont pas exécutés, seront considérés comme des commandes distinctes.
                            <br> 12. Envois partiels: les envois partiels de commandes sur sollicitation du client seront considérés comme des commandes séparées.
                            <br> 13. Garantie: tous les modèles sont garantis pour une période de 2 ans à compter de la date du document de vente Vetreria Vistosi Srl.
                            <br> 14. Exclusions: les prix indiqués dans cette liste de prix ne comprennent pas la TVA, les emballages spéciaux, les palettes, les ampoules et l’ensemble lorsqu’ils ne sont pas expressément indiqués.
                            <br> 15. livraisons: départ usine. Sauf accord contraire, publié dans la zone réservée du site Web « MyVistosi » de la société <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>.
                            <br> 16. Juridiction: pour tout litige de quelque nature qu’il s’agisse et survenant en tout lieu, le tribunal de Venise sera compétent et la loi applicable sera la loi italienne.
                        </dd>
                    </dl>
                </c:when>
                <c:otherwise>
                    <h2><spring:message code="condc.label.condl" text="Sales conditions"/></h2>
                    <dl>
                        <dt>Customer service web site: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Validity of this price list from 01.01.2021 to 31.12.2021
                            <br><br>
                            1. Personalized conditions of sales: the following are General conditions of sale.The detailed and personalized conditions of sale are published in the MyVistosi portal (<a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>). The conditions of sale will be valid for one year expiring on 31/12, without tacit renewal. Customers who do not purchase any products for a period exceeding 12 months will be considered inactive and all personalized sales conditions will no longer be in force.
                            <br> 2. Non-standard products: in the case of orders for products that are not listed in the price list, customized products or products with modifications from the standard, Vetreria Vistosi Srl reserves the right to make offers with conditions of sale different from those agreed.
                            <br> 3. Delivery times and stock availability: for goods not in stock, the average delivery times are 3/4 weeks. Stocks are updated daily and are available on the MyVistosi portal.
                            <br> 4. Inspection of goods upon receipt: the Customer is required to carefully check the goods before delivering them or sending them to the end user. Complaints will not be accepted for goods not available at the place of destination indicated in the transport documents.
                            <br> 5. Any damage to goods caused during transport: any transport damage to goods or to the packaging of goods, evident at the time of delivery, as well as differences in quantity and / or model compared to what is indicated in the transport document, must be reported with specific and detailed reservation in the courier waybill.
                            <br> 6. Hidden damage during transport: any transport damage to goods, not evident at the time of delivery or not reasonably foreseeable at the time of delivery, must be reported within 8 days from the delivery, in the complaints section of the MyVistosi portal.
                            <br> 7. Characteristics of Murano glass: the completely manual processing, the complex melting processes, the refinement of the chemical mixtures of Vistosi glass, typical of the ancient processings techniques of Murano glass, can give rise to slight inaccuracies in the colors, in the shapes and sizes, thicknesses and decorations. Rather than imperfections or quality differences, they must be considered an expression of hand-worked and mouth-blown glass processing. Small irregular bubbles, veins, small dots of dense material and other imperfections must therefore be tolerated, especially in the more complex shapes and processes or in medium-large models.
                            <br> 8. Product modification reserve: Vetreria Vistosi Srl reserves the right to modify its products at any time and without notice. The customer who has specific needs or who should combine the models ordered with others already in his possession must specify this in the order.
                            <br> 9. Discontinued models: Vetreria Vistosi Srl reserves the right to remove one or more models from production without giving prior notice. The Customer agree to promptly report any project or quotation in order to allow the Company to take them into account in the choice of models to be removed from production and discontinued.
                            <br> 10. Order management: orders will be considered accepted only when the order confirmation is sent by e-mail to the address indicated by the Customer and reported in the Customer data shit in the MyVistosi portal. The Customer undertakes to request changes to the order confirmations for any reason no later than 2 working days from receipt of the order confirmation. After this deadline, orders will not be modifiable or cancellable.
                            <br> 11. Additions to orders: additions to orders that, due to lack of stock availability, remain unfulfilled, will be considered as separate orders.
                            <br> 12. Partial shipments: partial shipments of orders upon customer reminder will be considered as separate orders.
                            <br> 13. Warranty: all models are guaranteed for a period of 2 years from the date of the Vetreria Vistosi Srl sales document.
                            <br> 14. Exclusions: the prices indicated in this price list do not include VAT, special packaging, pallets, light bulbs and all when not expressly indicated.
                            <br> 15. Return of goods: Ex Works. Unless otherwise agreed, published in the reserved area of the company website <a href="http://www.vistosi.it/portal">www.vistosi.it/portal</a>.
                            <br> 16. Jurisdiction: for any dispute of any kind and arising in any place the court of Venice will be competent and the applicable law will be the Italian one.
                        </dd>
                    </dl>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
</div>

