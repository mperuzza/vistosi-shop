<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<security:authentication property="principal.cliente.archclie.cdlist" var="cdlist"/>
<div>
    <c:choose>
        <c:when test="${cdlist == 'L01'}">
            <c:choose><c:when test="${rc.locale.language =='it'}">
                    <h2><spring:message code="condc.label.condl" text="Condizioni fisse da listino"/></h2>
                    <dl>
                        <dt>Portale per i servizi alla clientela: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Validit&agrave; del presente listino: fino al 31/12/2019
                            <br><br>
                            1) Le seguenti condizioni di vendita sono generali. Le condizioni di vendita dettagliate e personalizzate sono pubblicate nel portale MyVistosi - www.vistosi.it/portal. Il Cliente potr&agrave; avervi accesso diretto anche dalle conferme d’ordine. Le condizioni personalizzate di base sono: sconto, modalit&agrave; di pagamento, modalit&agrave; di trasporto, fido e minimi d’ordine. Le condizioni di vendita saranno rinnovate ogni inizio d’anno. I Clienti che non acquisteranno alcun prodotto per un periodo superiore a 12 mesi saranno considerati inattivi e tutte le condizioni di vendita personalizzate non saranno più in vigore.   
                            <br>
                            2) In caso di ordini di prodotti fuori listino, di prodotti a disegno o di prodotti con modifiche allo standard, Vetreria Vistosi Srl si riserva il diritto di formulare delle offerte con condizioni di vendita diverse da quelle accordate.  
                            <br>
                            3) Per la merce non a magazzino, i tempi di consegna medi sono di 3-4 settimane. Le giacenze sono aggiornate quotidianamente nel portale MyVistosi.
                            <br>
                            4) Gli imballi sono severamente testati. Il Cliente è tenuto a controllare accuratamente la merce prima di consegnarla o di spedirla al Cliente finale. Non saranno accettati reclami per merce non presente nel luogo di destinazione indicato nei documenti di trasporto. 
                            <br>5) Eventuali danni da trasporto alla merce o all’imballo della merce, evidenti al momento della consegna, come pure differenze di quantit&agrave; e/o di modello rispetto a quanto indicato nel documento di trasporto, devono essere segnalate con riserva specifica e dettagliata nella lettera di vettura del corriere.
                            <br>6) Eventuali danni da trasporto alla merce, non evidenti al momento della consegna o non ragionevolmente presumibili al momento della consegna, devono essere segnalati entro 8 gg dalla consegna nella sezione reclami del portale MyVistosi. 
                            <br>7) Le lavorazioni completamente manuali, i complessi processi di fusione, la raffinatezza delle miscele chimiche dei vetri di Vistosi e tipiche delle antiche tecniche di lavorazione di Murano, possono dar luogo a leggere imprecisioni dei colori, nelle forme e nelle dimensioni, negli spessori e nei decori. Più che imperfezioni o difformit&agrave; di qualit&agrave; sono da considerarsi espressione del vetro lavorato a mano e soffiato a bocca. Piccole bolle irregolari, venature, piccoli puntini di materiale denso e altre imperfezioni devono quindi essere tollerate, in particolar modo nelle forme e nelle lavorazioni più complesse o nei modelli di medio-grandi dimensioni. 
                            <br>8) Vetreria Vistosi Srl si riserva di modificare in qualsiasi momento e senza preavviso qualsiasi modello. Il cliente che dovesse avere necessit&agrave; specifiche o che dovesse abbinare i modelli ordinati ad altri gi&agrave; in suo possesso deve specificarlo nell'ordine. 
                            <br>9) Vetreria Vistosi Srl si riserva il diritto di togliere dalla produzione uno o più modelli senza darne preavviso. Il Cliente si impegna a segnalare tempestivamente qualsiasi progetto o quotazione da evadere per dare modo a Vistosi di tenerne conto nella scelta dei modelli da togliere dalla produzione. 
                            <br>10) Le disponibilit&agrave; di magazzino e i tempi di consegna di ogni modello sono indicate e aggiornate quotidianamente nel portale MyVistosi. 
                            <br>11) Le aggiunte d'ordine che, per mancanza di disponibilit&agrave; di magazzino, restassero inevase, verranno considerate come ordini a sé stanti. 
                            <br>12) Spedizioni parziali di ordini su sollecito del cliente, salvo ritardi di spedizione rispetto alla conferma d'ordine, verranno considerate come ordini a sé stanti. 
                            <br>13) Gli ordini saranno considerati accettati solo al momento dell’invio della conferma d’ordine via email all’indirizzo indicato dal Cliente e riportato nell5) Eventuali danni da trasporto alla merce o all’imballo della merce, evidenti al momento della consegna, come pure differenze di quantit&agrave; e/o di modello rispetto a quanto indicato nel documento di trasporto, devono essere segnalate con riserva specifica e dettagliata nella lettera di vettura del corriere.a scheda Cliente nel portale MyVistosi. Il Cliente si impegna a richiedere modifiche alle conferme d’ordine per qualsiasi ragione entro e non oltre 2 gg lavorativi dal ricevimento della conferma d’ordine. Trascorso questo termine gli ordini non saranno cancellabili o modificabili.
                            <br>14) Tutti i modelli sono garantiti per un periodo di 2 anni dalla data del documento di vendita di Vetreria Vistosi Srl. La garanzia non copre errori di installazione, errori di uso e manutenzione del prodotto, rimozione della componentistica. Le istruzioni di montaggio sono parte integrante del prodotto. 
                            <br>15) L’imballo standard &egrave; compreso nel prezzo, i bancali non sono compresi nel prezzo.
                            <br>16) Le lampadine non sono incluse nel prezzo. 
                            <br>17) Per ogni eventuale controversia di qualsiasi genere e sorta in qualsiasi luogo sar&agrave; competente il foro di Venezia e la legge applicabile sar&agrave; quella italiana.
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${rc.locale.language =='de'}">
                    <h2><spring:message code="condc.label.condl" text="Verkaufsbedingungen"/></h2>
                    <dl>
                        <dt>Portal für den Kundenservice: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Gültigkeit der Preislîste: bis zum 31/12/2019
                            <br><br>1) Die Waren werden vor dem Versand gewissenhaft ausgewählt und sorgfältig verpackt und die Verpackungen werden unter Belastung getestet. Wir übernehmen keine Haftung für Schäden, die während
                            des Transports oder im Lager des Kunden verursacht werden.
                            <br>2) Abweichungen von den Daten in den Transportdokumenten oder offensichtliche Schäden müssen sofort an den Spediteur, oder an den Lagerverantwortlichen, falls die Ware ab Werk geliefert wird,
                            gemeldet werden. Sobald die Person, die für die Entgegennahme der Waren zuständig ist, die Transportdokumente unterschreibt, akzeptiert sie den Zustand und die angegebenen Mengen, außer wenn
                            ein spezifischer Vorbehalt auf dem Frachtbrief des Transporteurs oder auf dem Beförderungspapier der Vetreria Vistosi Srl schriftlich ausgedrückt wird. Eine Kopie dieses Vorbehalts muss sofort auch zur
                            Vetreria Vistosi per Fax oder E-Mail geschickt werden.
                            <br>3) Offensichtliche Transportschäden, wie z. B. beschädigte Verpackungen, oder Verpackungen, die suspekte Geräusche wahrnehmen lassen, müssen schriftlich auf dem Frachtbrief an den Transporteur
                            gemeldet werden.
                            <br>4) Versteckte Transportschäden (die bei der Entgegennahme der Waren nicht sichtbar sind) müssen innerhalb von 8 Tagen nach Erhalt der Ware auf dem Portal MyVistosi in dem Beschwerdenbereich gemeldet
                            werden. Bei der Schadenmeldung müssen Fotos von allen Seiten der Verpackungen und von dem Schaden, mit der Ware vorzugsweise innerhalb des Kartons oder sofort nach dem Herausziehen
                            des Produktes, beigefügt werden. Das Produkt darf noch nicht installiert sein. Es muss bewiesen werden, dass der Schaden zum Zeitpunkt der Entgegennahme nicht sichtbar war.
                            <br>5) Der Kunde ist verpflichtet, die Waren sorgfältig zu prüfen, bevor er sie an den Endkunden aushändigt oder sie von seinem Lager versendet. Wir werden keine Beanstandungen über Waren akzeptieren,
                            die nicht an dem in Vistosis Dokumenten angegebenen Bestimmungsort vorhanden sind, oder die zuvor transportiert und dann zum gleichen Ort wiedergebracht wurden.
                            <br>6) Alle Sendungen mit Lieferung 'ab Werk' müssen während des Transports unter Verantwortung und zu Lasten vom Kunden versichert werden.
                            <br>7) Die vollkommene Handverarbeitung der Produkte, die komplexen Schmelzvorgänge, die Feinheit der chemischen Mischungen von Vistosis Gläsern, die typisch für die alten Verarbeitungstechniken von Murano
                            sind, können zu leichten Ungenauigkeiten bei Farben, Formen, Größe, Dicke und Dekoration führen; diese Besonderheiten sind nicht als Unvollkommenheiten, sondern als Effekte des handgefertigten
                            und mundgeblasenen Glases zu betrachten. Auf der Webseite www.vistosi.it kann man Eindrücke über viele dieser Verfahren bekommen. Kleine, unregelmäßige Blasen, Maserungen, kleine Punkte aus
                            dichtem Material und andere Ungenauigkeiten sollen toleriert werden, vor allem bei komplexeren oder größeren Formen und Produkten. Im Wissen, dass das perfekte handwerkliche Glas glücklicherweise
                            nicht existiert, stellt jedes Stück ein Unikat dar.
                            <br>8) Es gelten für alle spezifischen Verkaufsbedingungen, wie u.a. Lieferung, Zahlungsmodalität usw., die Auftragsbestätigung und die Bedingungen, die zum Bestellungszeitpunkt im Kundenaccount innerhalb
                            des Portals MyVistosi veröffentlicht sind.
                            <br>9) Das Unternehmen behält sich das Recht vor, jederzeit und ohne Vorankündigung jedes Modell zu ändern. Der Kunde, der einen besonderen Bedarf haben sollte, oder der die bestellten Modelle mit anderen
                            schon in seinem Besitz befindenden Modellen kombinieren möchte, muss dies bei der Bestellung angeben.
                            <br>10) Ungefähre Produktionszeit: ca. 3-4 Wochen, wenn nicht auf Lager. Der Lagerbestand wird täglich in dem Portal MyVistosi aktualisiert, zu dem jeder Kunde von Vistosi rund um die Uhr einen reservierten
                            und persönlichen Zugang hat. Für maßgefertigte Produkte werden die Lieferzeiten von Fall zu Fall geschätzt.
                            <br>11) Die Erweiterungen einer Bestellung, die wegen Mangels an Lagervorrat offen bleiben, werden als separate Bestellungen behandelt.
                            <br>12) Die vom Kunden gewünschten Teilsendungen einer Bestellung werden, mit Ausnahme von Lieferverzögerungen in Bezug auf die Auftragsbestätigung, als separate Bestellungen behandelt.
                            <br>13) Um Fälschungen zu vermeiden, haben die Produkte von Vistosi eine besondere Verpackung sowie eine Markierung des Glases und der Strukturen, die ausführlicher auf der Webseite www.vistosi.it
                            beschrieben werden.
                            <br>14) Standard-Verpackung im Preis inbegriffen.
                            <br>15) Leuchtmittel sind nicht im Preis inbegriffen.
                            <br>16) Für jegliche Streitfragen ist der Gerichtshof von Treviso zuständig und das Italienische Gesetz rechtskräftig.
                            <br>17) Vetreria Vistosi behält sich das Recht vor, die Produktion eines oder mehrere Modelle ohne Vorankündigung einzustellen. 
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${rc.locale.language =='es'}">
                    <h2><spring:message code="condc.label.condl" text="Condiciones de venta"/></h2>
                    <dl>
                        <dt>Portal de atención al cliente: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            Lista de precios válida hasta el 31/12/2019
                            <br><br>
                            1) El producto ha sido atentamente seleccionado y cuidadosamente empaquetado antes del envío y el embalaje ha sido previamente testado. Se declina por tanto cualquier responsabilidad por daños
                            causados durante el transporte o en el almacén del cliente.
                            <br>2) Las diferencias de mercancía respecto a la documentación de transporte o los daños evidentes deben de comunicarse inmediatamente al transportista o al responsable en caso de retiro en nuestro
                            almacén. En el momento en que la persona encargada de la recepción de la mercancía firma la documentación del transporte acepta las condiciones y cantidades indicadas, salvo indicación contraria
                            escrita de la documentación del transportista o de la Vetreria Vistosi Srl. Una copia de la incidencia debe enviarse inmediatamente también a Vetreria Vistosi por fax o email.
                            <br>3) Los daños evidentes provocados durante el transporte, como cajas dañadas o que hacen ruido que dejan entender que hay una rotura, deben indicarse por escrito en la documentación del transportista.
                            <br>4) Los daños de transporte ocultos (No visibles por tanto en el momento de la recepción de la mercancía) deben indicarse a través del portal MyVistosi en la sección reclamaciones en el plazo máximo de 8
                            días, adjuntando las fotos de todos los lados de la caja y del producto dañado, si es posible todavía en el interior de la caja o nada más retirado del embalaje. En ningún caso con el producto ya instalado
                            de modo que sea evidente que el daño no era visible al momento de la entrega.
                            <br>5) El cliente es responsable de revisar minuciosamente la mercancía antes de entregarla al cliente final o de colocarla en su propio almacén. No se aceptarán reclamaciones de artículos en lugares diferentes
                            a la dirección de entrega especificada en el documento de transporte o que hayan sido devueltos al mismo desde otro lugar por otro medio de transporte diferente.
                            <br>6) El seguro de todos los pedidos retirados en fábrica (EXW) corre a cargo del cliente.
                            <br>7) La elaboración completamente artesanal, la compleja fusión del vidrio, la precisión química necesaria para la producción del vidrio de Vistosi, propia de la tradición de Murano, pueden dar lugar a ligeras
                            diferencias en términos de color, forma y dimensión. Más que imprecisiones deben de considerarse un testimonio de la naturaleza artesanal del vidrio trabajado manualmente y soplado a boca. En la
                            página www.vistosi.it se pueden ver diferentes ejemplos de elaboración. Las burbujas, venas, motas de material denso y otras imperfecciones se consideran tolerables, especialmente en modelos de
                            especial dificultad o tamaño. Dando por hecho que el vidrio artesanal perfecto no existe, ya que cada pieza es única e irrepetible.
                            <br>8) Las condiciones específicas de venta como devoluciones, métodos de pago, etc están especificadas en la confirmación de pedido y las condiciones generales en vigor que se encuentran en el área
                            reservada del portal MyVistosi.
                            <br>9) La empresa se reserva el derecho de modificar en cualquier momento y sin previo aviso cualquier modelo. Para necesidades específicas del cliente, por ejemplo coincidencias con versiones anteriores
                            del modelo, recomendamos especificarlo en el pedido.
                            <br>10) El tiempo de producción indicativo es de 3 a 4 semanas en caso de no disponibilidad en el almacén. Las incidencias de existencias se actualizan regularmente en el portal MyVistosi al que todos los
                            clientes tienen acceso reservado y personalizado 24 horas al día. Para productos a medida los tiempos de entrega se estiman en el momento de confirmar el pedido.
                            <br>11) Las adiciones a un pedido en curso, y que por falta de disponibilidad en el almacén, no pudieran entregarse, se considerarán como pedidos en firme.
                            <br>12) Los envíos de una parte del pedido solicitados por el cliente, salvo aquellos debidos a retrasos en el envío con respecto a la confirmación del pedido, serán considerados como pedidos en firme.
                            <br>13) Los productos de Vistosi tienen un embalaje especial y un marcado tanto en el vidrio como en la estructura metálica para evitar falsificaciones, para mayor información: www.vistosi.it.
                            <br>14) El embalaje standard está incluido en el precio.
                            <br>15) Las bombillas no están incluidas en el precio.
                            <br>16) Para cualquier disputa el foro competente es el de Venecia y la ley aplicable la italiana.
                            <br>17) Vetreria Vistosi se reserva el derecho a eliminar de su produccion uno o mas modelos sin preaviso.
                        </dd>   
                    </dl>


                </c:when>
                <c:when test="${rc.locale.language =='fr'}">
                    <h2><spring:message code="condc.label.condl" text="Conditions de vente"/></h2>
                    <dl>
                        <dt>Portail web pour le service clients: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            La présente liste des prix est valable jusqu'au: 31/12/2019
                            <br><br>
                            1) Les produits sont attentivement sélectionnés et soigneusement emballés avant l'expédition et les emballages sont soumis à de tests sévères. Nous déclinons toute responsabilité pour les dommages
                            causés au cours du transport ou dans l'entrepôt du client.
                            <br>2) Toute divergence par rapport aux données contenues dans les documents de transport ou d'éventuels dommages évidents doivent être immédiatement signalés au transporteur, ou bien au responsable
                            du dépôt pour les livraisons départ usine. Au moment où le préposé au retrait de la marchandise signe des documents de transport, il en accepte les conditions et les quantités indiquées, sauf réserve
                            spécifique écrite sur la lettre de voiture du transporteur ou sur le document de transport de la Vetreria Vistosi Srl. Une copie de cette réserve doit être immédiatement envoyée par fax ou par courriel à la
                            Vetreria Vistosi aussi.
                            <br>3) Les dommages et avaries de transport évidents, tels que des emballages endommagés ou produisant des bruits suspects, doivent être signalés à l'écrit sur la lettre de voiture au transporteur.
                            <br>4) Les dommages et avaries de transport occultes (non visibles au moment de la réception de la marchandise) doivent être signalés dans le portail web MyVistosi, à la section réclamations, dans un délai de
                            8 jours à compter de la réception, en joignant les photos de tous les côtés des boîtes et du dommage, si possible à l'intérieur de la boîte ou juste après avoir extrait le produit, et non après avoir installé
                            le produit. Le but est de prouver que le dommage n'était pas visible au moment du retrait.
                            <br>5) Le client est tenu de contrôler soigneusement la marchandise avant de la remettre au client final ou de l'expédier de ses entrepôts. Nous n'accepterons pas de réclamations à propos de produits non
                            présents sur le lieu de destination indiqué sur les documents de Vistosi ou antérieurement transportés et puis ramenés au même lieu.
                            <br>6) Tous les envois à livraison 'départ usine' doivent être assurés pendant le transport sous la responsabilité et aux soins du client.
                            <br>7) Le travail entièrement fait à la main, les processus complexes de fusion, le raffinement des mélanges chimiques des verres de Vistosi, typiques des anciennes techniques artisanales de Murano, peuvent
                            comporter de légères imprécisions dans les couleurs, les formes et les dimensions, dans les épaisseurs et les décorations; plutôt que des imperfections, il faut considérer celles-ci comme l'expression
                            du verre travaillé à la main et soufflé à la bouche. Sur le site www.vistosi.it on peut apprécier ces processus. De petites bulles irrégulières, des veinures, de petits points de matière dense et d'autres
                            imperfections doivent être tolérés, surtout chez les formes et les travaux les plus complexes ou de plus grande taille. Tout en sachant qu'heureusement le verre artisanal parfait n'existe pas, ce qui rend
                            chaque pièce vraiment unique.
                            <br>8) Pour toutes les conditions spécifiques de vente telles que livraison, moyens de paiement, etc., la confirmation de commande fera foi, ainsi que les conditions publiées au moment du passage de la
                            commande dans l'espace réservé au client à l'intérieur du portail MyVistosi.
                            <br>9) L'entreprise se réserve le droit, à tout moment et sans préavis, de modifier les modèles. Le client ayant des exigences spécifiques, ou devant combiner les modèles commandés à d'autres qu'il possède
                            déjà, doit préciser cela lors de la commande.
                            <br>10) Délais de production indicatifs: 3-4 semaines environ, si non disponible en stock. Les stocks sont mis à jour quotidiennement dans le portail MyVistosi, auquel tous les clients de Vistosi ont un accès
                            réservé et personnalisé 24 heures sur 24. Les délais de livraisons pour les produits fabriqués sur mesure seront estimés au cas par cas.
                            <br>11) Les ajouts de commande qui, à cause du manque de disponibilité en stock, ne seraient pas exécutés, seront considérés comme une commande indépendante.
                            <br>12) Les envois partiels de commandes sur demande du client, sauf pour les retards de livraison par rapport à la confirmation de commande, seront considérés comme une commande indépendante.
                            <br>13) Les produits de Vistosi ont un emballage particulier et un marquage des verres aussi bien que des structures, mieux décrites sur le site www.vistosi.it, afin d'en prévenir la contrefaçon.
                            <br>14) Emballage standard compris dans le prix.
                            <br>15) Les ampoules ne sont pas comprises dans le prix.
                            <br>16) Toute éventuelle contestation sera soumise à la compétence du Tribunal de Venise et donc à la loi italienne.
                            <br>17) Vetreria Vistosi se réserve le droit d'éliminer de la production un ou plusieurs modèles sans préavis.
                        </dd>
                    </dl>

                </c:when>
                <c:otherwise>
                    <h2><spring:message code="condc.label.condl" text="Sales conditions"/></h2>
                    <dl>
                        <dt>Customer service web site: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>Validity of the current price list: until 31/12/2019
                            <br><br>
                            1) The following are General Conditions of Sale. The detailed and personalized conditions of sale are published in the MyVistosi portal - www.vistosi.it/portal. The Customer may have direct access to them from the order confirmations, too. The basic customized conditions are: discount, payment methods, mode of transport, credit and minimum order. The conditions of sale will be renewed at the beginning of each year. The Customers that do not buy any product for a period longer than 12 months will be considered inactive and all conditions of sale will no longer be in force.   
                            <br>2) In the case of orders for products that are not listed in the price list, products that are based on drawings or products with modifications to the standard, Vetreria Vistosi Srl may reserve the right to make offers with different conditions of sale from those agreed.  
                            <br>3) For goods not in stock, the average delivery time is 3-4 weeks. Stocks are updated daily in the MyVistosi portal.
                            <br>4) Packaging is severely tested. The Customer is required to check carefully the goods before delivering or shipping them to the final Customer. No claims will be accepted for goods not available at the place of destination that was indicated in the transport documents. 
                            <br>5) Any damage to goods in transit or to the packaging of goods, evident at the time of delivery, as well as differences in quantity and / or model compared to what is indicated in the transport document, must be reported as “goods unchecked” and that must be detailed in the waybill of the courier.
                            <br>6) Any damage to goods in transit, not evident at the time of delivery or not reasonably foreseeable at the time of delivery, must be reported within 8 days from the delivery in the complaints section of the portal MyVistosi. 
                            <br>7) Manual processing, elaborate melting processes, the refinement of chemical mixtures of Vistosi glass, which are typical of the ancient processing techniques of Murano, may give rise to slight inaccuracies in colour, shape and size, thickness and decoration. They must be considered as the result of handmade and mouth-blown glass procession, rather than imperfections or differences in quality. Small irregular bubbles, veins, small dots of dense material and other imperfections must therefore be tolerated, especially in the most intricate shapes and processes or in medium-large models. 
                            <br>8) Vetreria Vistosi Srl may reserve the right to modify any model at any time and without notice. Should Customers have specific needs or combine the models ordered with others already in their possession, they must specify that in the order. 
                            <br>9) Vetreria Vistosi Srl may reserve the right to discontinue one or more models without notice. Customers agree to promptly report any project or quotation to be processed in order to allow Vistosi to take that into account at the time of choosing the models to be discontinued. 
                            <br>10) Stock availability and delivery times of each model are indicated and updated daily in the MyVistosi portal. 
                            <br>11) Additions to orders that, due to lack of stock availability, remain unfulfilled, will be considered as separate orders. 
                            <br>12) Partial shipments of orders on request of the Customer, except for delays in shipment with respect to the order confirmation, will be considered as separate orders. 
                            <br>13) Orders will be considered accepted only only when the order confirmation is sent by email to the address indicated by the Customer and reported in the Customer data sheet in the MyVistosi portal. The Customer agrees to request changes to the order confirmations for any reason within and no later than 2 working days from receipt of the order confirmation. After this period, orders cannot be cancelled or modified.
                            <br>14) All models are warranted for a period of 2 years from the date of the sales document of Vetreria Vistosi Srl. The warranty does not cover installation errors, errors in use and maintenance of the product, removal of components. The assembly instructions are an integral part of the product. 
                            <br>15) Standard packaging is included in the price, pallets are not included in the price.
                            <br>16) Bulbs are not included in the price. 
                            <br>17) For any dispute of any kind and arising in any place, the Court of Venice will be competent and the Italian law shall be the only one applicable.   
                        </dd>
                    </dl>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${cdlist == 'LUS'}">
            <h2><spring:message code="condc.label.condl" text="Sales conditions"/></h2>
            <dl>
                <dt>Customer service web site: MyVistosi - www.vistosi.it/portal</dt>
                <dd>
                    Validity of the current price list: until 01/01/2019
                    <br><br>
                    <br>1) The goods are carefully selected and packaged before being sent, and the packaging is strictly tested. The Company declines all responsibility for any damage occurred during transportation or storage
                    in the client's warehouse.
                    <br>2) Any discrepancy between the delivered items and the information included in the transport documents, or any clear damage must be immediately reported to the forwarder or, in case of an ex-factory
                    delivery, to the warehouse supervisor. The signature of the transport documents by the person in charge of receiving the goods implies the acceptance of the conditions and quantities indicated, unless
                    otherwise reported on the forwarder's bill of loading or in the transport documents issued by Vetreria Vistosi Srl.
                    <br>3) Any clear damage caused by transportation, such as damaged packages or packages which, from the way they sound, seem to contain something broken, must be reported on the forwarder's transport
                    documents.
                    <br>4) Any unseen damage (not visible when picking up the goods) must be reported within 8 days in the complaint section of the MyVistosi portal, attaching the photpgraphs of all the sides of the package and
                    of the damage, possibly when the product is still in the package or immediately after the unwrapping, not when the product is installed, in order to demonstrate that the damage was not visible at the time
                    of delivery. A copy of the claim must be immediately sent to Vistosi via fax or email.
                    <br>5) The client is bound to carefully check the goods before delivering them to the final client or to ship them from his warehouses. Vistosi Srl will not accept complaints regarding goods which are not present
                    in the destination indicated on the documents issued by Vistosi or regarding goods which have been moved and then transported back to the same place.
                    <br>6) The client must provide transport insurance for all ex-factory deliveries.
                    <br>7) The completely manual work, the fusion processes, the subtlety of the Vistosi glass' chemical blends, which are characteristic of the Murano glass manufacturing, are often complex and can cause slight
                    imperfections in the products' colour, shape and dimensions, or in their thickness and decoration; more than imperfections they ought to be considered as peculiarities of the handmade blown glass. Many
                    of these processes can be observed on the website www.vistosi.it. Some small irregular bubbles in the glass, grains, spots of denser material and other imperfections should be tolerated, especially when
                    they affect the bigger or more complex shapes and processing. A handcrafted perfect glass, unfortunately, does not exist, but precisely for this reason each piece is unique and impossible to replicate.
                    <br>8) For all specific terms of sale such as refunds, terms of payment, etc., the reference documents will be the order confirmation and the conditions that will appear in the client area of the MyVistosi portal
                    when the order is placed.
                    <br>9) Vetreria Vistosi S.r.l. reserves the rights, without any notice, to modify any of its models. Whenever a client has a special request or needs to match the ordered items with other items he already purchased,
                    he will have to specify it in the order.
                    <br>10) Estimate production time: approximately 3-4 weeks, if not to warehouse. The stock is updated daily on the MyVistosi portal, which can be visited in a customized and personal way by any Vistosi client
                    24/7. Delivery time for bespoke versions: to be estimated.
                    <br>11) Any additional order which, due to unavailable stock, should not be satisfied, will be considered as a separate order.
                    <br>12) Partial deliveries requested by the client, unless they are due to a delayed delivery with respect to the order confirmation, will be considered as a separate order.
                    <br>13) The Vistosi products have a special packaging and, in order to avoid counterfeiting, both their glasses and structures have a marking that is described on the website www.vistosi.it.
                    <br>14) Standard packaging is included in the price.
                    <br>15) Light bulbs are not included in the price.
                    <br>16) Any dispute will be settled by the Venice jurisdiction according to the Italian laws.
                    <br>17) Vetreria Vistosi reserves the right to discontinue one or more products without notice.
                </dd>
            </dl>
        </c:when>
        <c:when test="${cdlist == 'LCA'}">
            <c:choose>
                <c:when test="${rc.locale.language =='fr'}">
                    <h2><spring:message code="condc.label.condl" text="Conditions de vente"/></h2> 
                    <dl>
                        <dt>Portail web pour le service clients: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            La présente liste des prix est valable jusqu'au: 01/01/2019
                            <br><br>
                            <br>1) Les produits sont attentivement sélectionnés et soigneusement emballés avant l'expédition et les emballages sont soumis à de tests sévères. Nous déclinons toute responsabilité pour les dommages
                            causés au cours du transport ou dans l'entrepôt du client.
                            <br>2) Toute divergence par rapport aux données contenues dans les documents de transport ou d'éventuels dommages évidents doivent être immédiatement signalés au transporteur, ou bien au responsable
                            du dépôt pour les livraisons départ usine. Au moment où le préposé au retrait de la marchandise signe des documents de transport, il en accepte les conditions et les quantités indiquées, sauf réserve
                            spécifique écrite sur la lettre de voiture du transporteur ou sur le document de transport de la Vetreria Vistosi Srl. Une copie de cette réserve doit être immédiatement envoyée par fax ou par courriel à la
                            Vetreria Vistosi aussi.
                            <br>3) Les dommages et avaries de transport évidents, tels que des emballages endommagés ou produisant des bruits suspects, doivent être signalés à l'écrit sur la lettre de voiture au transporteur.
                            <br>4) Les dommages et avaries de transport occultes (non visibles au moment de la réception de la marchandise) doivent être signalés dans le portail web MyVistosi, à la section réclamations, dans un délai de
                            8 jours à compter de la réception, en joignant les photos de tous les côtés des boîtes et du dommage, si possible à l'intérieur de la boîte ou juste après avoir extrait le produit, et non après avoir installé
                            le produit. Le but est de prouver que le dommage n'était pas visible au moment du retrait.
                            <br>5) Le client est tenu de contrôler soigneusement la marchandise avant de la remettre au client final ou de l'expédier de ses entrepôts. Nous n'accepterons pas de réclamations à propos de produits non
                            présents sur le lieu de destination indiqué sur les documents de Vistosi ou antérieurement transportés et puis ramenés au même lieu.
                            <br>6) Tous les envois à livraison 'départ usine' doivent être assurés pendant le transport sous la responsabilité et aux soins du client.
                            <br>7) Le travail entièrement fait à la main, les processus complexes de fusion, le raffinement des mélanges chimiques des verres de Vistosi, typiques des anciennes techniques artisanales de Murano, peuvent
                            comporter de légères imprécisions dans les couleurs, les formes et les dimensions, dans les épaisseurs et les décorations; plutôt que des imperfections, il faut considérer celles-ci comme l'expression
                            du verre travaillé à la main et soufflé à la bouche. Sur le site www.vistosi.it on peut apprécier ces processus. De petites bulles irrégulières, des veinures, de petits points de matière dense et d'autres
                            imperfections doivent être tolérés, surtout chez les formes et les travaux les plus complexes ou de plus grande taille. Tout en sachant qu'heureusement le verre artisanal parfait n'existe pas, ce qui rend
                            chaque pièce vraiment unique.
                            <br>8) Pour toutes les conditions spécifiques de vente telles que livraison, moyens de paiement, etc., la confirmation de commande fera foi, ainsi que les conditions publiées au moment du passage de la
                            commande dans l'espace réservé au client à l'intérieur du portail MyVistosi.
                            <br>9) L'entreprise se réserve le droit, à tout moment et sans préavis, de modifier les modèles. Le client ayant des exigences spécifiques, ou devant combiner les modèles commandés à d'autres qu'il possède
                            déjà, doit préciser cela lors de la commande.
                            <br>10) Délais de production indicatifs: 3-4 semaines environ, si non disponible en stock. Les stocks sont mis à jour quotidiennement dans le portail MyVistosi, auquel tous les clients de Vistosi ont un accès
                            réservé et personnalisé 24 heures sur 24. Les délais de livraisons pour les produits fabriqués sur mesure seront estimés au cas par cas.
                            <br>11) Les ajouts de commande qui, à cause du manque de disponibilité en stock, ne seraient pas exécutés, seront considérés comme une commande indépendante.
                            <br>12) Les envois partiels de commandes sur demande du client, sauf pour les retards de livraison par rapport à la confirmation de commande, seront considérés comme une commande indépendante.
                            <br>13) Les produits de Vistosi ont un emballage particulier et un marquage des verres aussi bien que des structures, mieux décrites sur le site www.vistosi.it, afin d'en prévenir la contrefaçon.
                            <br>14) Emballage standard compris dans le prix.
                            <br>15) Les ampoules ne sont pas comprises dans le prix.
                            <br>16) Toute éventuelle contestation sera soumise à la compétence du Tribunal de Venise et donc à la loi italienne.
                            <br>17) Vetreria Vistosi se réserve le droit d'éliminer de la production un ou plusieurs modèles sans préavis.
                        </dd>
                    </dl>                                      
                </c:when>
                <c:otherwise>
                    <h2><spring:message code="condc.label.condl" text="Sales conditions"/></h2> 
                    <dl>
                        <dt>Customer service web site: MyVistosi - www.vistosi.it/portal</dt>
                        <dd>
                            Validity of the current price list: until 01/01/2019
                            <br><br>
                            1) The goods are carefully selected and packaged before being sent, and the packaging is strictly tested. The Company declines all responsibility for any damage occurred during transportation or storage
                            in the client's warehouse.
                            <br>2) Any discrepancy between the delivered items and the information included in the transport documents, or any clear damage must be immediately reported to the forwarder or, in case of an ex-factory
                            delivery, to the warehouse supervisor. The signature of the transport documents by the person in charge of receiving the goods implies the acceptance of the conditions and quantities indicated, unless
                            otherwise reported on the forwarder's bill of loading or in the transport documents issued by Vetreria Vistosi Srl.
                            <br>3) Any clear damage caused by transportation, such as damaged packages or packages which, from the way they sound, seem to contain something broken, must be reported on the forwarder's transport
                            documents.
                            <br>4) Any unseen damage (not visible when picking up the goods) must be reported within 8 days in the complaint section of the MyVistosi portal, attaching the photpgraphs of all the sides of the package and
                            of the damage, possibly when the product is still in the package or immediately after the unwrapping, not when the product is installed, in order to demonstrate that the damage was not visible at the time
                            of delivery. A copy of the claim must be immediately sent to Vistosi via fax or email.
                            <br>5) The client is bound to carefully check the goods before delivering them to the final client or to ship them from his warehouses. Vistosi Srl will not accept complaints regarding goods which are not present
                            in the destination indicated on the documents issued by Vistosi or regarding goods which have been moved and then transported back to the same place.
                            <br>6) The client must provide transport insurance for all ex-factory deliveries.
                            <br>7) The completely manual work, the fusion processes, the subtlety of the Vistosi glass' chemical blends, which are characteristic of the Murano glass manufacturing, are often complex and can cause slight
                            imperfections in the products' colour, shape and dimensions, or in their thickness and decoration; more than imperfections they ought to be considered as peculiarities of the handmade blown glass. Many
                            of these processes can be observed on the website www.vistosi.it. Some small irregular bubbles in the glass, grains, spots of denser material and other imperfections should be tolerated, especially when
                            they affect the bigger or more complex shapes and processing. A handcrafted perfect glass, unfortunately, does not exist, but precisely for this reason each piece is unique and impossible to replicate.
                            <br>8) For all specific terms of sale such as refunds, terms of payment, etc., the reference documents will be the order confirmation and the conditions that will appear in the client area of the MyVistosi portal
                            when the order is placed.
                            <br>9) Vetreria Vistosi S.r.l. reserves the rights, without any notice, to modify any of its models. Whenever a client has a special request or needs to match the ordered items with other items he already purchased,
                            he will have to specify it in the order.
                            <br>10) Estimate production time: approximately 3-4 weeks, if not to warehouse. The stock is updated daily on the MyVistosi portal, which can be visited in a customized and personal way by any Vistosi client
                            24/7. Delivery time for bespoke versions: to be estimated.
                            <br>11) Any additional order which, due to unavailable stock, should not be satisfied, will be considered as a separate order.
                            <br>12) Partial deliveries requested by the client, unless they are due to a delayed delivery with respect to the order confirmation, will be considered as a separate order.
                            <br>13) The Vistosi products have a special packaging and, in order to avoid counterfeiting, both their glasses and structures have a marking that is described on the website www.vistosi.it.
                            <br>14) Standard packaging is included in the price.
                            <br>15) Light bulbs are not included in the price.
                            <br>16) Any dispute will be settled by the Venice jurisdiction according to the Italian laws.
                            <br>17) Vetreria Vistosi reserves the right to discontinue one or more products without notice.
                        </dd> 
                    </dl>
                </c:otherwise>
            </c:choose>
        </c:when>        
    </c:choose>
</div>

