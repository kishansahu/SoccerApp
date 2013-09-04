jQuery.fn.DrawCanvas = function (myJsonVariable) /* call : $('#test').DrawCanvas(); */
{
    $.extend(myJsonVariable);
    return this.each (function () 
    {   c=document.getElementById(this.id);
        ctx=c.getContext("2d");
        c.addEventListener("click", onCanvasClick, false);

        var PointCollection = [];
        var maxWidth;
        var pixelPerMinute,pixelPerTimeGap,pixelLoop;
        var time,timeGap; 

        //ball images
        ballImg = new Image();
        ballImg.src = myJsonVariable.ballImg;
        arrowImg = new Image();
        arrowImg.src = myJsonVariable.arrowImg;;	

        // Set field parameter
        setFieldParameter();

        //Positioning Field
        drawField();
	drawParameter();

        // Positioning elapsed Time
        //drawTimeLine(time);
        
        // To set the field parameter
        function setFieldParameter()
        {
            time = parseInt(myJsonVariable.TimeElasped); 
            timeGap = myJsonVariable.TimeGap != ""?parseInt(myJsonVariable.TimeGap) : 5;
            pixelLoop = Math.floor(90/timeGap)+1;
            if(time < 90)
            {
		maxWidth = c.width - 15;
                pixelPerMinute = maxWidth/90;                
            }
            else
            {
		maxWidth = c.width - 45;
                pixelPerMinute =maxWidth/90;
            }
            pixelPerTimeGap = pixelPerMinute * timeGap;
        }
		
        function drawParameter()
        {
            // positioning cards.
            var redCardForTopTeam = myJsonVariable.TeamTopRedCard;
            var redCardForBottomTeam = myJsonVariable.TeamBottomRedCard;
            var yellowCardForTopTeam = myJsonVariable.TeamTopYellowCard;
            var yellowCardForBottomTeam = myJsonVariable.TeamBottomYellowCard;

            setCard(redCardForTopTeam,'Red',true);
            setCard(redCardForBottomTeam,'Red',false);

            setCard(yellowCardForTopTeam,'Yellow',true);
            setCard(yellowCardForBottomTeam,'Yellow',false);

            // positioning goals.
            var goalForTopTeam = myJsonVariable.TeamTopGoal;
            var goalForBottomTeam = myJsonVariable.TeamBottomGoal;

            setGoal(goalForTopTeam,true);
            setGoal(goalForBottomTeam,false);

            // positioning replacement.
            var replacementForTopTeam = myJsonVariable.TeamTopReplacement;
            var replacementForBottomTeam = myJsonVariable.TeamTopReplacement;

            setReplacement(replacementForTopTeam,true);
            setReplacement(replacementForBottomTeam,false);
        }

        // To draw elapsed time line
        function drawTimeLine(time)
        {
            var timeElapsed;
            timeElapsed = pixelPerMinute*time;
            ctx.fillStyle = "rgba(255, 255, 255, 0.5)";
            ctx.fillRect(timeElapsed,c.height/4,maxWidth,c.height/2); // botx,topx,boty,topy
            ctx.fill();
        }

       // To draw field
        function drawField()
        {
            // Green Area
            var botx=0, boty=c.height, topx=0, topy=0, bx=pixelPerTimeGap, tx=pixelPerTimeGap;
            var color = ["#6AA828","#559412"];//6AA828,559412
            var text=0;
            for(var i=0;i<pixelLoop;i++)
            {
                ctx.fillStyle = color[i%2];//"#33CC33";//"#164C19";
		ctx.fillRect(botx,c.height/4,botx+pixelPerTimeGap,c.height/2); // botx,topx,boty,topy
                ctx.fill();
                //drawLine(botx,boty-c.height/2);
                if(i>0 && text <=90)
                {
                    ctx.font = "11px Helvetica, sans-serif";
                    ctx.strokeStyle="white";
                    ctx.fillStyle = "white";
                    ctx.strokeText(text===0?timeGap:text,text===0? botx-15:botx-18, 55);
                    if(text===0)
                            text+=parseInt(timeGap);
                    text+=parseInt(timeGap);
                }
                botx += bx;
               //console.log(botx);
            }
            if(time > 90)
            {
                ctx.fillStyle = "grey";//rgba(255, 255, 255, 0.5)";
                ctx.fillRect(botx-pixelPerTimeGap,c.height/4,c.width,c.height/2); // botx,topx,boty,topy
                ctx.fill();
            }
        }

        // To draw time line
        function drawLine(x,y)
        {
            ctx.beginPath();
            ctx.moveTo(x,c.height/3);//ctx.moveTo(349,0);
            ctx.lineTo(x,y);//ctx.lineTo(356,70);
            // ctx.closePath();
            ctx.lineWidth=1;
            ctx.strokeStyle='#929C93';//"#00459C";
            ctx.closePath();
            ctx.stroke();
            ctx.strokeStyle=null;
        }

        // To set the card
        function setCard(cardList,color,top)
        {
            if(cardList != "")
            {		
                var List = cardList.split("|"); 
                var card = [];
                for(var i=0;i<List.length;i++)
                {
                    var str = List[i].split(",");
                    card[i] = [str[0],str[1]];
                }
                // Positioning Cards
                drawCard(card,color,top); 
            }
        }

        // To set the goal
        function setGoal(goalList,top)
        {
            if(goalList != "")
            {		
                var List = goalList.split("|"); 
                var goal = [];
                for(var i=0;i<List.length;i++)
                {
                    var str = List[i].split(",");
                    goal[i] = [str[0],str[1]];
                }
                // Positioning Goals
                drawGoal(goal,top); 
            }
        }

        // To set the replacement
        function setReplacement(repalcementList,top)
        {
            if(repalcementList != "")
            {		
                var List = repalcementList.split("|"); 
                var replacement = [];
                for(var i=0;i<List.length;i++)
                {
                    var str = List[i].split(",");
                    replacement[i] = [str[0],str[1]];
                }
                // Position Replacements
                drawReplacement(replacement,top); 
            }
        }

        // To set the card position
        function drawCard(Card,color,team)
        {
            if(Card.length > 0)
            {
                var timeElapsed;
                ctx.fillStyle=color;
                for(var i=0; i< Card.length; i++)
                {
                    timeElapsed = Card[i][0]*pixelPerMinute;
                    if(team === true)
                    {
                        ctx.fillRect(timeElapsed-4,10,8,15); // botx,topx,boty,topy
                        PointCollection.push({
                                message: Card[i][0].toString() + "' : "+ Card[i][1],
                                width: 8,
                                height: 15,
                                x: timeElapsed-4,
                                y: 10,
                                isAbove:!team,
                                isBelow:team
                        });
                    }
                    else
                    {
                        ctx.fillRect(timeElapsed-4,75,8,15);
                        PointCollection.push({
                                message: Card[i][0].toString() + "' : "+ Card[i][1],
                                width: 8,
                                height: 15,
                                x: timeElapsed-4,
                                y: 75,
                                isAbove:!team,
                                isBelow:team
                        });
                    }
                    ctx.fill();
                }
            }
        }

        // To set the goal position
        function drawGoal(Goal,team)
        {
            if(Goal.length > 0)
            {
				//alert(ballImg.src);
                var timeElapsed;
                for(var i=0; i< Goal.length; i++)
                {
                    ctx.beginPath();
                    ctx.fillStyle = 'red';
                    timeElapsed = Goal[i][0]*pixelPerMinute;
					//alert(timeElapsed);
                    if( team === true)
                    {
                       ctx.drawImage(ballImg, timeElapsed-8, 15,15,15);
                       PointCollection.push({
                                    message: Goal[i][0].toString()+ "' : " + Goal[i][1],
                                    width: 10,
                                    height: 10,
                                    x: timeElapsed-8, 
                                    y: 15,   
                                    isAbove:!team,
                                    isBelow:team
                            });
                    }
                    else
                    {
                        ctx.drawImage(ballImg, timeElapsed-8, 70,15,15);
                        PointCollection.push({
                                message: Goal[i][0].toString()+ "' : " + Goal[i][1], 
                                width: 10,
                                height: 10,
                                x: timeElapsed-8,
                                y: 70, 
                                isAbove:!team,
                                isBelow:team
                        });
                    }
                    ctx.fill();
                    ctx.stroke();
                }
            }
        }

        // To set the replacement position
        function drawReplacement(Replacement,team)
        {
            if(Replacement.length > 0)
            {
                var timeElapsed;
                for(var i=0; i< Replacement.length; i++)
                {
                    ctx.beginPath();
                    ctx.fillStyle = 'red';
                    timeElapsed = Replacement[i][0]*pixelPerMinute;
                    if( team === true)
                    {
                       // ctx.arc(timeElapsed+10, 25, 5, 0, 2 * Math.PI, false);
                        ctx.drawImage(arrowImg, timeElapsed-4, 10,15,15);
                        PointCollection.push({
                               message: Replacement[i][0].toString()+ "' : " + Replacement[i][1],
                                width: 15,
                                height: 15,
                                x: timeElapsed-4, 
                                y: 10,   
                                isAbove:!team,
                                isBelow:team
                        });
                    }
                    else
                    {
                        ctx.drawImage(arrowImg, timeElapsed-4, 75,15,15);
                        PointCollection.push({
                                message: Replacement[i][0].toString()+ "' : " + Replacement[i][1],
                                width: 15,
                                height: 15,
                                x: timeElapsed-4,
                                y: 75, 
                                isAbove:!team,
                                isBelow:team
                        });
                    }
                    ctx.fill();
                    ctx.stroke();
                }
            }
        }

        // To handle click event
        function onCanvasClick(e) 
        {
            if(e.offsetX && e.offsetY)
            {
                var x=e.offsetX;
                var y=e.offsetY;
            }
            else
            {
                var x=e.layerX;
                var y=e.layerY;
            }
            for(i=0;i<PointCollection.length;i++)
            {
               if( x >= PointCollection[i].x && x <= (PointCollection[i].x+PointCollection[i].width) &&   
                       y >= PointCollection[i].y && y<=(PointCollection[i].y+PointCollection[i].height))
               {
                    /*$(".canvas-message")
                            .text(PointCollection[i].message)
                            .css("top",y-50+"px")
                            .css("left",x+10+"px")
                            .show(); */
                    $(".canvas-message").text(PointCollection[i].message);
                    var messageDiv=$(".canvas-message").show().get()[0];
                    var messageDivHeight=messageDiv.offsetHeight;
                    var messageDivWidth=messageDiv.offsetWidth;
                    if(PointCollection[i].isAbove)
                    {
                         $(".canvas-message")
                                 .css("top",PointCollection[i].y - messageDivHeight -5  +"px")
                                 .css("left",PointCollection[i].x - (messageDivWidth/2) +"px");
                    }
                    else
                    {
                         $(".canvas-message")
                                 .css("top", PointCollection[i].y + PointCollection[i].height + 5 +"px")
                                 .css("left",PointCollection[i].x - (messageDivWidth/2) + "px");
                    }
                    break;
               }
               else
               {
                   $(".canvas-message").hide();
               }
            }
        }
    });
}

