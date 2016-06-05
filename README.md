# PunchCard

## 第10组团队项目总结及成员贡献率
### 项目概要介绍
目前市场上手机软件中的背单词、运动APP都融入了很多的社交功能。当手机APP有了社交分享之后，对于产品推广、增大用户群体有着非常重要的意义。对于用户来说，“打卡”、“签到”等活动也能帮助自己坚持做一件事情。
经过以上的需求分析，我们实现的一款基于Android平台的具有社交媒体分享功能的“打卡”软件（我们命名为PunchCard），用户可以选择自己要坚持做的“项目”。为了简化，我们就以“背单词”为例子。类似“背单词”这种事情本来是一件枯燥、不容易坚持的事情。我们设计用户背单词获取相应的奖励。我们预先设计这样的规则：每天背一定数量的单词，换取相应的金币；每天签到打卡能换取一定金币等。金币的作用可以让自己在好友中排名靠前，也可以换取相应的实物奖励、或者相应的道具等。我们用统计图来展示用户的完成情况，以及在社交媒体的好友中所在的排名位置。
在PunchCard的设计阶段，我们进行了体系结构设计（包括数据流和控制流的复审、功能和程序的交叉引用）、设计数据库和形成的数据结构，规定服务器和客户端的接口设计。此外，我们还绘出了原始模型（如图1），为下一步编码进行准备。
 
图1 – 原型设计
在编码阶段，组内进行了明确的分工：美工设计由郑晓钿负责；Android客户端前由刘子涵和黄若孜负责；Android客户端负责通讯部分的和服务器API由刘旺和刘思远负责。在测试阶段，我们进行了单元测试和系统测试，基本满足了需求，对于测试过程中发现编码疏漏和错误进行修改；在服务器端还进行了代码覆盖度测试，且结果比较理想。下面是界面的展示（图2）。
   
图2 – 界面展示
对于整个项目，我们按照依照规约中时间计划完成软件工程生命周期的各个阶段，从需求分析到设计，从编码到测试，组员分工明确、各自发挥自己的特长。在整个过程中，体验和学习了计算机软件的制造作为一门系统工程的流程和方法。在编码测试过程中遇到的问题，通过团队的作用很容易能解决，在这个过程中，小组的每个成员也学到了很多知识。通过这次项目，对团队协作有了新的理解，认识到软件工程中协作的重要性。我发现通过实践认识到软件工程是一门在计算机科学中重要的学科。
最后，感谢每位成员对本项目的努力和付出。
表1 - 第10组分工
<table>
	<tr> <td>成员</td> <td>分工</td> </tr>
  <tr> <td>刘旺、刘思远</td> <td>服务器端编码、部署、测试等以及客户端的通讯部分</td> </tr>
  <tr> <td>郑晓钿</td> <td>客户端美工、逻辑交互的设计，参与系统测试</td> </tr>
<tr> <td>刘子涵、黄若孜</td> <td>Android客户端前端部分实现和测试</td> </tr>
</table>
	
	
	
### 附件
[服务器部署地址](http://ali.leasunhy.com:8000)；
[项目展示视频地址](http://liuw53-wp.stor.sinaapp.com/se%2F20.mp4)；
[项目服务器端源代码仓库](https://github.com/coolspring1293/PunchCardAPI)；
[项目Android客户端源代码仓库](https://github.com/coolspring1293/PunchCard)；
[六次会议记录（含照片）](https://github.com/coolspring1293/PunchCard/issues)。




### The MIT License (MIT)

CopyRight (c) 2014 vellow  &lt;<a href="mailto:i@vellow.net">i@vellow.net</a>&gt;

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

