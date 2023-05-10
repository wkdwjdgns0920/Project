<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resource/startPage.css" />
<script src="/resource/startPage.js" defer="defer"></script>
</head>
<body>
<script type="text/javascript">
import Splitting from 'https://cdn.skypack.dev/splitting'

const TRIGGERS = document.querySelectorAll('.contents li')
const BACKDROPS = document.querySelectorAll('.backdrops li')
const SCROLLER = document.querySelector('.scroller')

// For each TRIGGER set up a ViewTimeline
// Then create WAAPI for each backdrop

const TIMELINES = []

TRIGGERS.forEach(subject => {
  TIMELINES.push(new ViewTimeline({
    subject,
    axis: 'block'
  }))
})

BACKDROPS.forEach((backdrop, index) => {
  const IMG = backdrop.querySelector('img')

  IMG.animate([{
    transform: 'translateY(0%)'
  }
  ], {
    timeline: TIMELINES[index - 1],
    fill: 'both',
    delay: {
      phase: 'exit', 
      percent: CSS.percent(5),
    },
    endDelay: {
      phase: 'exit', 
      percent: CSS.percent(75),
    }
  })

  IMG.animate([{
    transform: 'translateY(-50%)'
  }
  ], {
    timeline: TIMELINES[index],
    fill: 'both',
    delay: {
      phase: 'exit', 
      percent: CSS.percent(5),
    },
    endDelay: {
      phase: 'exit', 
      percent: CSS.percent(100),
    }
  })
})

// Split the progress wheel
Splitting()

SCROLLER.animate([
  {
    transform: 'rotate(360deg)'
  }
], {
  fill: 'both',
  timeline: TIMELINES[TIMELINES.length - 1],
  delay: {
    phase: 'enter',
    percent: CSS.percent(-200)
  },
  endDelay: {
    phase: 'enter',
    percent: CSS.percent(100)
  }
})
</script>
<div class="scroller" data-splitting="">
  Scroll•Scroll•
</div>
<main>
  <ul class="backdrops">
    <li>
      <img src="https://www.yuseong.go.kr/thumbnail/trrsrt/TR_202105110257537201.jpg" width="1240" alt="">
    </li>
    <li>
      <img src="https://images.unsplash.com/photo-1519608487953-e999c86e7455?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=1080&ixid=MnwxfDB8MXxyYW5kb218MHx8Y3liZXJwdW5rfHx8fHx8MTY2NDIyNzYxOQ&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=1920" width="1920" alt="">
    </li>
    <li>
      <img src="https://images.unsplash.com/photo-1515036551567-bf1198cccc35?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=1080&ixid=MnwxfDB8MXxyYW5kb218MHx8Y3liZXJwdW5rfHx8fHx8MTY2NDI5NTgxNQ&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=1920" width="1920" alt="">
    </li>
    <li>
      <img src="https://images.unsplash.com/photo-1573455494060-c5595004fb6c?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=1080&ixid=MnwxfDB8MXxyYW5kb218MHx8Y3liZXJwdW5rfHx8fHx8MTY2NDI5NTgyNA&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=1920" width="1920" alt="">
    </li>
  </ul>
  <ul class="contents">
    <li>
      <h2>Scroll</h2>
    </li>
    <li>
      <h2>Scroll more</h2>
    </li>
    <li>
      <h2>Scroll further</h2>
    </li>
    <li>
      <h2>That's it!</h2>
    </li>
  </ul>
</main>
</body>
</html>