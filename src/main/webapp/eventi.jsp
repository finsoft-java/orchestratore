<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

		<section class="content-header">
			<h1>{{labels.menu.eventi}}</h1>
		</section>
		
		<section class="content">
		
			<div id="divDettagliCalendarioMilestone" >
				<div class="col-md-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">{{labels.global.eventi}}</h3>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table id="tableEventi" class="table no-margin">
						        	<thead>
							          <tr role="row">
							           <th class="col-md-2">Data e ora</th>
							           <th class="col-md-2">Codice tipo evento</th>
							           <th class="col-md-2">Codice entità</th>
							           <th class="col-md-2">Acronimo</th>
							           <th class="col-md-3">Tag</th>
							          </tr>
							        </thead>          
						        </table>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</section>

		<script src="dist/js/eventi.js"></script>

</t:template>